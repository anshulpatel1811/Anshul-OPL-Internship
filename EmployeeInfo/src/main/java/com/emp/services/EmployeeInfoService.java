package com.emp.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.emp.entities.EmployeeInfo;

public interface EmployeeInfoService {

	EmployeeInfo saveEmployeeInfo(EmployeeInfo employeeInfo, MultipartFile file);
	
//	String uploadXLData(MultipartFile file);
//	
//	byte[] generateExcelFile() throws IOException;
}
