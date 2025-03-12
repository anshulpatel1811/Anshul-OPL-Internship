package com.emp.security.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.emp.security.proxy.EmployeeProxy;
import com.emp.security.proxy.ImageProxy;
import com.emp.security.proxy.LoginRequest;
import com.emp.security.proxy.LoginResponse;

public interface EmpService {

	// save employee
	EmployeeProxy saveData(EmployeeProxy employeeProxy);
	
	// get employee by id
	EmployeeProxy getById(String id);
	
	// get All employee
	Page<EmployeeProxy> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	// update employee
	EmployeeProxy updatedEmployee(String id,EmployeeProxy employeeProxy);
	
	// delete employee
	void deleteEmpById(String id);
	
	// delete All employee
	void deleteAllEmp();
	
	// for login
	public LoginResponse login(LoginRequest request);
	
	// save Employee Data And Image
	String saveEmployeeInfo(EmployeeProxy employeeInfo, MultipartFile file) throws IOException;
	
	// get file by byte[]
	//byte[] getImage(String path,String name) throws IOException;
	
	// get file
	ImageProxy getResource(String name) throws FileNotFoundException;
}
