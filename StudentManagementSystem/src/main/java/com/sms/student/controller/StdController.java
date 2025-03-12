package com.sms.student.controller;

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

import com.sms.student.proxy.BranchProxy;
import com.sms.student.proxy.StudentProxy;
import com.sms.student.service.StudentServices;
import com.sms.student.util.ApiResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("student")
public class StdController {

	@Autowired
	private StudentServices services;
	
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<StudentProxy>> findAllStudent(){
		
		return new ResponseEntity<>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentProxy> findById(@PathVariable String id){
		
		return new ResponseEntity<>(services.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/saveStudent")
	public ResponseEntity<StudentProxy> createStudent(@Valid @RequestBody StudentProxy proxy){
		return new ResponseEntity<StudentProxy>(services.create(proxy), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<StudentProxy> updateStudentById(@Valid @PathVariable String id,@RequestBody StudentProxy proxy){
		return new ResponseEntity<StudentProxy>(services.updateById(proxy, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public ResponseEntity<ApiResponseMessage> deteleStudentById(@PathVariable String id){
		return new ResponseEntity<>(services.deleteById(id), HttpStatus.OK);
	}
	
	
}
