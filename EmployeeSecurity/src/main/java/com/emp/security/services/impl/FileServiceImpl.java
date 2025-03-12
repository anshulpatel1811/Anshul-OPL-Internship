package com.emp.security.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.security.exception.BadApiRequest;
import com.emp.security.proxy.EmployeeProxy;
import com.emp.security.services.EmpService;
import com.emp.security.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private EmpService empService;
	
	@Override
	public String uploadFile(MultipartFile file, String path) throws IOException {
		
		// orignal-file name
		String originalFilename = file.getOriginalFilename();
		// random file name
		UUID filename = UUID.randomUUID();
		
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String fileNameWithExtension = filename+extension;
		
		String fullPathWithExtension = path+fileNameWithExtension;
		
		if(extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg")) {
			
			File folder = new File(path);
			
			if(!folder.exists()) {
				folder.mkdirs();
			}
			Files.copy(file.getInputStream(), Paths.get(fullPathWithExtension));
			return fileNameWithExtension;
		}else {
			throw new BadApiRequest("File with this "+extension+" not allowed !!");
		}
	
	}

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException {
		String fullPath = path+File.separator+name;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

//	@Override
//	public byte[] getImage(String path, String name) throws IOException {
//
//		EmployeeProxy proxy = empService.getById(name);
//		String imageName = proxy.getImageName();
//		String fullPath = path+imageName;
//		byte[] bs = Files.readAllBytes(Paths.get(fullPath));
//		
//		
//		return bs;
//	}

}

