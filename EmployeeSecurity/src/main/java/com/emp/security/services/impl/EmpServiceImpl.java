package com.emp.security.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.security.domain.Employee;
import com.emp.security.domain.Image;
import com.emp.security.exception.ResourceNotFoundException;
import com.emp.security.proxy.EmployeeProxy;
import com.emp.security.proxy.ImageProxy;
import com.emp.security.proxy.LoginRequest;
import com.emp.security.proxy.LoginResponse;
import com.emp.security.repositories.EmpRepository;
import com.emp.security.repositories.ImageRepository;
import com.emp.security.services.EmpService;
import com.emp.security.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmpRepository repository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ImageRepository repository2;
	
	// saveData
	@Override
	public EmployeeProxy saveData(EmployeeProxy employeeProxy) {
		Employee employee = mapper.convertValue(employeeProxy, Employee.class);
		employee.setEmpId(UUID.randomUUID().toString());
		employee.setEmpPassword(encoder.encode(employeeProxy.getEmpPassword()));
		Employee savedEmployee = repository.save(employee);
		return mapper.convertValue(savedEmployee, EmployeeProxy.class);
	}

	// get Employee By id
	@Override
	public EmployeeProxy getById(String id) {
		
		Employee employee = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found With Given ID !!"));
		return mapper.convertValue(employee, EmployeeProxy.class);
	}

	// get All Employee
	@Override
	public Page<EmployeeProxy> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Employee> hpPage = repository.findAll(pageable);

		List<EmployeeProxy> collect = hpPage.getContent().stream().map(hp -> mapper.convertValue(hp, EmployeeProxy.class))
				.collect(Collectors.toList());

		Page<EmployeeProxy> pages = new PageImpl<EmployeeProxy>(collect, pageable, hpPage.getTotalElements());

		return pages;
	}

	// update Employee
	@Override
	public EmployeeProxy updatedEmployee(String id, EmployeeProxy employeeProxy) {
		Employee employee = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found With Given ID !!"));
		employee.setEmpName(employeeProxy.getEmpName());
		employee.setEmpRole(employeeProxy.getEmpRole());
		employee.setIsActive(employeeProxy.getIsActive());
		//employee.setImageName(employeeProxy.getImageName());
		Employee updatedEmployee = repository.save(employee);
		return mapper.convertValue(updatedEmployee, EmployeeProxy.class);
	}

	// delete By Employee Id
	@Override
	public void deleteEmpById(String id) {
		Employee employee = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found With Given ID !!"));
		repository.delete(employee);
	}

	// delete All Employee
	@Override
	public void deleteAllEmp() {
		repository.deleteAll();
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		//Employee employee = repository.findByEmpName(request.getUsername()).orElseThrow(()-> new RuntimeException("Data Not Found With Given Input !!"));
		
		Authentication unverifiedAuth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
		
		Authentication verifiedAuth = manager.authenticate(unverifiedAuth);
		
		if(verifiedAuth.isAuthenticated()) {
			return new LoginResponse(request.getUsername(),jwtUtils.generateToken(request.getUsername()),(List<SimpleGrantedAuthority>) verifiedAuth.getAuthorities());
		}
		
		return new LoginResponse(request.getUsername(), "Failed Request !!",null);
	}

	@Override
	public String saveEmployeeInfo(EmployeeProxy employeeProxy, MultipartFile file) throws IOException {
//		
//		Employee employee = mapper.convertValue(employeeProxy, Employee.class);
// 		employee.setEmpId(UUID.randomUUID().toString());
// 		employee.setEmpPassword(encoder.encode(employeeProxy.getEmpPassword()));
// 		
//		
// 		// orignal-file name
// 		String originalFilename = file.getOriginalFilename();
// 		// random file name
// 		UUID filename = UUID.randomUUID();
// 			
// 		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
// 			
// 		String fileNameWithExtension = filename+extension;
// 			
// 		String fullPathWithExtension = path+fileNameWithExtension;
// 			
// 		if(extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg")) {
// 				
// 			File folder = new File(path);
// 				
// 			if(!folder.exists()) {
// 				folder.mkdirs();
// 			}
// 				
// 			Files.copy(file.getInputStream(), Paths.get(fullPathWithExtension));
// 				
// 			}else {
// 			throw new BadApiRequest("File with this "+extension+" not allowed !!");
// 		}
// 			
// 			Employee savedEmployee = repository.save(employee);
//
// 		//EmployeeProxy employeeProxy = empService.getById(id);
// 		EmployeeProxy proxy = getById(savedEmployee.getEmpId());
// 		proxy.setImageName(filename.toString());
// 		//empService.updatedEmployee(id, employeeProxy);
// 		EmployeeProxy updatedEmployee = updatedEmployee(savedEmployee.getEmpId(), proxy);
// 			
//		//Employee employee2 = repository.findById(savedEmployee.getEmpId()).get();
//
//		 return updatedEmployee;
//		 // return mapper.convertValue(savedEmployee, EmployeeProxy.class);  // Save employee info in DB
//	   
		try {
			employeeProxy.setEmpPassword(encoder.encode(employeeProxy.getEmpPassword()));
			Employee employee = mapper.convertValue(employeeProxy, Employee.class);
 
			String path = new ClassPathResource("").getFile().getAbsolutePath() + File.separator + "static"
					+ File.separator + "documents";
 
			File file2 = new File(path);
			if (!file2.exists()) {
				file2.mkdirs(); // Create directories if they don't exist
			}
 
			String filename = file.getOriginalFilename();
			String absolutePath = path + File.separator + filename;
 
			Files.copy(file.getInputStream(), Paths.get(absolutePath), StandardCopyOption.REPLACE_EXISTING);
 
			// Generate fileId (UUID for uniqueness)
			String fileId = UUID.randomUUID().toString().concat(file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(".")));
//		
			//ImageEntity profileImage = new ImageEntity();
			Image profileImage = new Image();
			profileImage.setContantType(file.getContentType());
			profileImage.setFileName(filename);
			profileImage.setFileId(fileId);
			profileImage.setFileSize(file.getSize());
//			emp.setFileId(fileId);
			repository2.save(profileImage);
 
			employee.setImages(profileImage);
			repository.save(employee);
 
			return "Employee save successfully.";
 
		}
		catch (IOException e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}



	@Override
	public ImageProxy getResource(String name) throws FileNotFoundException {
		Optional<Image> emp = repository2.findByFileId(name);
//		fileId
		System.out.println(emp.get());
 
		if (emp.isPresent()) {
			try {
				String path = new ClassPathResource("").getFile().getAbsolutePath() + File.separator + "static"
						+ File.separator + "documents" + File.separator + emp.get().getFileName();
 
				byte[] readAllBytes = Files.readAllBytes(Paths.get(path));
 
				emp.get().setFileData(readAllBytes);
 
				return mapper.convertValue(emp.get(), ImageProxy.class);
			} catch (Exception e) {
				throw new RuntimeException("Image not found.");
			}
		} else {
			throw new UsernameNotFoundException("User details not found in database with given id.");
		}
	}
	
//	@Override
//	public byte[] getImage(String path, String name) throws IOException {
//
//		EmployeeProxy proxy = getById(name);
//		//String imageName = proxy.getImageName();
//		//String fullPath = path+imageName;
//		//byte[] bs = Files.readAllBytes(Paths.get(fullPath));
//		
//		//Byte[] newByteArray = ArrayUtils.toObject(bs);
//		//return bs;
//	}
	

}
