package com.employee.services;

import java.util.List;

import com.employee.Dtos.EmployeeDto;

public interface EmployeeService {
	
	// create 
	EmployeeDto createEmployee(EmployeeDto dto);
	
	// update 
	EmployeeDto updateEmployee(EmployeeDto dto,Long id);

	// find by id
	EmployeeDto findById(Long id);
	
	// find all
	List<EmployeeDto> findAll();
	
	// delete by id
	void deletById(Long id);
	
	// delete all
	void deleteAll();
	
	// find all containing keyword 
	List<EmployeeDto> findAllByKeyword(String keyword);
}
