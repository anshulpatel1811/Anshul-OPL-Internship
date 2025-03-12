package com.ems.employee.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ems.employee.entity.EmployeeProfileImage;
import com.ems.employee.proxy.EmployeeProfileImageProxy;

public interface EmployeService {

	String uploadFile(MultipartFile file) throws IOException;
	
	EmployeeProfileImageProxy downloadOrServeFileImage(String fileId);
	
	//upload the file
	String uploadFile2(MultipartFile file,String path) throws IOException;
	
	// download or serve
	EmployeeProfileImageProxy downloadOrServeFileImage2(String fileId,String path);
	
	// get inputstream by path
	InputStream getResource(String path,String name) throws FileNotFoundException;
	
	EmployeeProfileImage getEmpById(String id);
	
	// upload multiple file
	void downloadOrServeFileImageMultiple(List<MultipartFile> file,String path);
	
	public ResponseEntity<byte[]> downloadListofMultiMediaDataAtDynamicPath(Long id);
}
