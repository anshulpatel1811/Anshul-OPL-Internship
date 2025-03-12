package com.emp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.emp.entities.EmployeeInfo;
import com.emp.services.EmployeeInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/employee")
public class EmployeeInfoController {

	 @Autowired
	 private EmployeeInfoService employeeInfoService;

	 @PostMapping("/upload")
	 public ResponseEntity<EmployeeInfo> uploadEmployeeInfo(
	         @RequestParam("file") MultipartFile file,  // The file being uploaded
	         @RequestParam("employee") String employeeJson  // The JSON data of the employee
	 ) {
	     try {
	         // Convert the employee JSON string to the EmployeeInfo object
	         ObjectMapper objectMapper = new ObjectMapper();
	         EmployeeInfo employeeInfo = objectMapper.readValue(employeeJson, EmployeeInfo.class);

	          // Save the employee information along with the file
	          EmployeeInfo savedEmployee = employeeInfoService.saveEmployeeInfo(employeeInfo, file);

	          return ResponseEntity.ok(savedEmployee);
	     } catch (Exception e) {
	         return ResponseEntity.badRequest().body(null);
	     }
	 }
	 
//	 @PostMapping("/uploadXL")
//	 public ResponseEntity<String> uploadXLData(@RequestParam("fileData") MultipartFile file){
//		System.out.println(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
//		 String uploadXLData = employeeInfoService.uploadXLData(file);
//		 return new ResponseEntity<String>(uploadXLData,HttpStatus.OK);
//	 }
//	
//	 @GetMapping("/download")
//	 public ResponseEntity<byte[]> downloadEmployeeData() throws IOException {
//	     byte[] excelFile = employeeInfoService.generateExcelFile();
//	        
//	     HttpHeaders headers = new HttpHeaders();
//	     headers.add("Content-Disposition", "attachment; filename=employee_data.xlsx");
//	     return ResponseEntity.ok()
//	              .headers(headers)
//	              .body(excelFile);
//	    }	 
	 
}
