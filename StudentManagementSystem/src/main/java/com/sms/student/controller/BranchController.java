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
import com.sms.student.service.BranchServices;
import com.sms.student.util.ApiResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("branch")
public class BranchController {

	@Autowired
	private BranchServices services;
	
	@GetMapping("/getAllBranch")
	public ResponseEntity<List<BranchProxy>> findAllBranch(){
		
		return new ResponseEntity<>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchProxy> findById(@PathVariable String id){
		
		return new ResponseEntity<>(services.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/saveBranch")
	public ResponseEntity<BranchProxy> createBranch(@Valid @RequestBody BranchProxy proxy){
		return new ResponseEntity<BranchProxy>(services.create(proxy), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateBranch/{id}")
	public ResponseEntity<BranchProxy> updateBranchById(@Valid @PathVariable String id,@RequestBody BranchProxy proxy){
		return new ResponseEntity<>(services.updateById(proxy, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBranch/{id}")
	public ResponseEntity<ApiResponseMessage> deteleBranchById(@PathVariable String id){
		return new ResponseEntity<>(services.deleteById(id), HttpStatus.OK);
	}

}
