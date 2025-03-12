package com.emp.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.entities.EmployeeInfo;
import com.emp.repositories.EmployeeInfoRepository;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService{

	@Autowired
    private EmployeeInfoRepository employeeInfoRepository;

//	@Override
//	public String uploadXLData(MultipartFile file) {
//		
//		try {
//			List<EmployeeInfo> list = DocumentHelper.getListOfObjectFromXL(file.getInputStream());
//			employeeInfoRepository.saveAll(list);
//			return "data saved !!";
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public byte[] generateExcelFile() throws IOException {
//		 // Fetch data from the database
//		List<EmployeeInfo> list = employeeInfoRepository.findAll();
//
//        // Generate Excel file and return as byte array
//        return ExcelGenerator.generateExcel(list);
//	}
	
	@Override
	public EmployeeInfo saveEmployeeInfo(EmployeeInfo employeeInfo, MultipartFile file) {
		 try {
	            if (file != null && !file.isEmpty()) {
	                // Convert the MultipartFile to byte array
	                byte[] fileData = file.getBytes();
	                employeeInfo.setProfileImageData(fileData);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            // Handle exception (log it or rethrow it)
	        }
	        return employeeInfoRepository.save(employeeInfo);  // Save employee info in DB
	   
	}

	
}
