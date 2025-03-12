package com.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.Dtos.ApiResponseMessage;
import com.employee.Dtos.EmployeeDto;
import com.employee.services.EmployeeService;
import com.employee.validate.IdNotNull;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	// create
	@GetMapping
	public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto dto){
		return new ResponseEntity<EmployeeDto>(service.createEmployee(dto), HttpStatus.CREATED);
	}
	
	// update
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto dto,@IdNotNull @PathVariable Long id){
		return new ResponseEntity<EmployeeDto>(service.updateEmployee(dto, id), HttpStatus.OK);
	}
	
	// find by id
	@PostMapping("/{id}")
	public ResponseEntity<EmployeeDto> findByIdEmployee(@NotNull @PathVariable String id){
		return new ResponseEntity<EmployeeDto>(service.findById(Long.parseLong(id)), HttpStatus.OK);
	}
	
	// find all
	@PostMapping("/findAll")
	public ResponseEntity<List<EmployeeDto>> findAllEmployee(
			
	){
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	// delete by id
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> deleteByIdEmployee(@IdNotNull @PathVariable Long id){
		service.deletById(id);
		ApiResponseMessage message = ApiResponseMessage.builder().message("Employee is deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	// delete all
	@DeleteMapping("/deleteAll")
	public ResponseEntity<ApiResponseMessage> deleteAllEmployee(){
		service.deleteAll();
		ApiResponseMessage message = ApiResponseMessage.builder().message("All Employee is deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	// find all by keyword
	@GetMapping("/{keyword}")
	public ResponseEntity<List<EmployeeDto>> findAllEmployeeByKeyword(@PathVariable String keyword){
		return new ResponseEntity<>(service.findAllByKeyword(keyword), HttpStatus.OK);
	}

}
