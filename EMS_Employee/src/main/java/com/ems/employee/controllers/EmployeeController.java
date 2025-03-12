package com.ems.employee.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ems.employee.entity.EmployeeProfileImage;
import com.ems.employee.proxy.EmployeeProfileImageProxy;
import com.ems.employee.services.EmployeService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeService service;
	
	@Value("${employee.profile.image.path}")
	private String imagePath;
	
	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity<String>(service.uploadFile(file), HttpStatus.CREATED);
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<?> download(@PathVariable("id") String id){
		EmployeeProfileImageProxy imageProxy = service.downloadOrServeFileImage(id);
		
		//To View
		//return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(imageProxy.getContentType())).body(imageProxy.getFileData());
		
		//To Download
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + imageProxy.getFileId() + "\"").body(imageProxy.getFileData());
	}
	
	@PostMapping("/upload2")
	public ResponseEntity<String> upload2(@RequestParam("file") MultipartFile file) throws IOException {	
		
		return new ResponseEntity<String>(service.uploadFile2(file, imagePath), HttpStatus.CREATED);
	}
	
	@GetMapping("/download2/{id}")
	public ResponseEntity<?> download2(@PathVariable("id") String id) throws IOException{
		EmployeeProfileImageProxy imageProxy = service.downloadOrServeFileImage2(id,imagePath);
		
		//To View
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(imageProxy.getContentType())).body(imageProxy.getFileData());
	}
	
	//serve user image
	@GetMapping("/image/{userId}")
	public void serveUserImage(@PathVariable("userId") String userId,HttpServletResponse response) throws IOException {
		EmployeeProfileImage empById = service.getEmpById(userId);
		
		InputStream resource = service.getResource(imagePath, empById.getFileId());
			
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			
		org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
//	
//		EmployeeProfileImageProxy imageProxy = service.downloadOrServeFileImage(userId);
//
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + imageProxy.getFileId() + "\"").body(imageProxy.getFileData());

	}
	
	@PostMapping("/upload-files")
	public ResponseEntity<?> uploadMultipleFiles(
			@RequestParam("image") List<MultipartFile> file
	){
		service.downloadOrServeFileImageMultiple(file, imagePath);
		return ResponseEntity.ok("file uploaded");
	}
	
	@GetMapping("/download-list-of-multimedia-at-dynamic-path/{id}")
	public ResponseEntity<?> downloadListofMultiMediaDataAtDynamicPath(@PathVariable("id") String id) {
		ResponseEntity<byte[]> emp = service.downloadListofMultiMediaDataAtDynamicPath(Long.parseLong(id));
 
		return emp;
	}
}
