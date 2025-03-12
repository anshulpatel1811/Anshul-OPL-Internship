package com.ems.employee.services.Impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ems.employee.entity.EmployeeInfo;
import com.ems.employee.entity.EmployeeProfileImage;
import com.ems.employee.enums.GenderType;
import com.ems.employee.exception.BadApiRequest;
import com.ems.employee.proxy.EmployeeProfileImageProxy;
import com.ems.employee.repositories.EmployeeInfoRepo;
import com.ems.employee.repositories.ProfileImageRepo;
import com.ems.employee.services.EmployeService;
import com.ems.employee.utils.ErrorResponse;
import com.ems.employee.utils.Mapper;

@Service
public class EmployeeServiceImpl implements EmployeService{

	@Autowired
	private ProfileImageRepo repo;
	
	@Autowired
	private EmployeeInfoRepo repo2;
	
	@Autowired
	private Mapper mapper;
	
	@Value("${employee.profile.image.path}")
	private String imagePath;
	
	@Override
	public String uploadFile(MultipartFile file) throws IOException {
		
		EmployeeProfileImage profileImage = new EmployeeProfileImage();
		profileImage.setFileName(file.getName());
		profileImage.setContentType(file.getContentType());
		profileImage.setFileName(file.getName());
		profileImage.setFileSize(file.getSize());
		profileImage.setFileData(file.getBytes());
		
		String Id = UUID.randomUUID().toString();
		String originalFilename = file.getOriginalFilename();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String fileNameWithExtension = Id+extension;
		profileImage.setFileId(fileNameWithExtension);
		repo.save(profileImage);
		
		return "Data Saved !!";
	}

	@Override
	public EmployeeProfileImageProxy downloadOrServeFileImage(String fileId) {
		Optional<EmployeeProfileImage> emp = repo.findByFileId(fileId);
		if(emp.isPresent()) {
			return mapper.EntityToProxy(emp.get());
		}
	
		ErrorResponse response = ErrorResponse.builder().message("No record found with this input !!").success(false).status(HttpStatus.BAD_REQUEST).build();
		
		return new EmployeeProfileImageProxy(response);
	}

	@Override
	public String uploadFile2(MultipartFile file, String path) throws IOException {
		
		String originalFilename = file.getOriginalFilename();
		
		String filename = UUID.randomUUID().toString();
		
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String fileNameWithExtension = filename+extension;
		
		String fullPathWithFileName=path+fileNameWithExtension;
		
		if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
			
		File folder = new File(path);
		if(!folder.exists()) {
			
			folder.mkdirs();
		}
		
		Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
		
		EmployeeProfileImage profileImage = new EmployeeProfileImage();
		profileImage.setFileName(file.getName());
		profileImage.setContentType(file.getContentType());
		profileImage.setFileName(file.getName());
		profileImage.setFileSize(file.getSize());
		profileImage.setFileData(null);
		
		profileImage.setFileId(fileNameWithExtension);
		repo.save(profileImage);
		
		return fileNameWithExtension;
		
		}else {
			throw new BadApiRequest("File with this "+extension+" not allowed !!");
		}
	}

	@Override
	public EmployeeProfileImageProxy downloadOrServeFileImage2(String fileId, String path) {
		EmployeeProfileImage empPI = null;
		try {
		
			empPI = repo.findByFileId(fileId).get();
			String absolutePath = path +empPI.getFileId();
			System.out.println(absolutePath);

			byte[] allBytes = Files.readAllBytes(new File(absolutePath).toPath());
			empPI.setFileData(allBytes);

		} catch (IOException e) {
			e.printStackTrace();

		}

		return mapper.EntityToProxy(empPI);
	}

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException {
		String fullPath = path+File.separator+name;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

	@Override
	public EmployeeProfileImage getEmpById(String id) {

		EmployeeProfileImage profileImage = repo.findByFileId(id).get();
		return profileImage;
	}

	@Override
	public void downloadOrServeFileImageMultiple(List<MultipartFile> file,String path) {
		
		List<EmployeeProfileImage> list = new ArrayList<>();
		
		for(MultipartFile ff : file) {
			String originalFilename = ff.getOriginalFilename();
			
			String filename = UUID.randomUUID().toString();
			
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			
			String fileNameWithExtension = filename+extension;
			
			String fullPathWithFileName=path+fileNameWithExtension;
			
			if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
				
			File folder = new File(path);
			if(!folder.exists()) {
				
				folder.mkdirs();
			}
			
			try {
				Files.copy(ff.getInputStream(), Paths.get(fullPathWithFileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			EmployeeProfileImage profileImage = new EmployeeProfileImage();
			//profileImage.setFileName(ff.getName());
			profileImage.setContentType(ff.getContentType());
			profileImage.setFileName(ff.getName());
			profileImage.setFileSize(ff.getSize());
			
			profileImage.setFileData(null);
			
			
			profileImage.setFileId(fileNameWithExtension);
			list.add(profileImage);
			
			}else {
				throw new BadApiRequest("File with this "+extension+" not allowed !!");
			}
		}
		
			EmployeeInfo employeeInfo = new EmployeeInfo();
			employeeInfo.setGender(GenderType.MALE);
			employeeInfo.setAddress("Address");
			employeeInfo.setDob("18-11-2002");
			employeeInfo.setName("Anshul");
			String string = UUID.randomUUID().toString();
			employeeInfo.setEmpId(string);
			employeeInfo.setProfileImage(list);
			repo2.save(employeeInfo);
	}

	@Override
	public ResponseEntity<byte[]> downloadListofMultiMediaDataAtDynamicPath(Long id) {
		
		// Find the EmployeeProfileImages associated with the given employee ID
	    List<EmployeeProfileImage> profileImages = repo.findByprofileImage(id);
 
	    if (profileImages.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
 
	    // Create ByteArrayOutputStream to hold the ZIP file contents
	    ByteArrayOutputStream zipBytes = new ByteArrayOutputStream();
	    ZipOutputStream zipOut = new ZipOutputStream(zipBytes);
 
//	    // Directory where files are stored (as per your upload method)
//		String fileDirectory = null;
//		try {
//			fileDirectory = new ClassPathResource("static/documents").getFile().getAbsolutePath();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
 
	    // Iterate over each EmployeeProfileImage and add the image to the ZIP file
	    for (EmployeeProfileImage profileImage : profileImages) {
	        String fileName = profileImage.getFileId();
	        String filePath = imagePath+ fileName;  // Construct the full file path
	        
	        System.out.println(fileName);
	        System.out.println(filePath);
	        File file = new File(filePath);
	        if (file.exists()) {
	            try (InputStream fileInputStream = new FileInputStream(file)) {
	                // Create a new entry in the ZIP file for each profile image
	                zipOut.putNextEntry(new ZipEntry(fileName));
	                IOUtils.copy(fileInputStream, zipOut);
	                zipOut.closeEntry();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
 
	    // Close the ZIP output stream
	    try {
	        zipOut.finish();
	        zipOut.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
 
	    // Set HTTP response headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", "profile_images.zip");
	    headers.setContentLength(zipBytes.size());
 
	    // Return the ZIP file as a byte array
	    return new ResponseEntity<>(zipBytes.toByteArray(), headers, HttpStatus.OK);
	}

	

}
