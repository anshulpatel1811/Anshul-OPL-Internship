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

import com.sms.student.proxy.MobileNumProxy;
import com.sms.student.service.MobileNumServices;
import com.sms.student.util.ApiResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("mobile")
public class MobileNumController {

	@Autowired
	private MobileNumServices services;
	
	@GetMapping("/getAllMobile")
	public ResponseEntity<List<MobileNumProxy>> findAllMobile(){
		
		return new ResponseEntity<>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MobileNumProxy> findById(@PathVariable String id){
		
		return new ResponseEntity<>(services.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/saveMobile")
	public ResponseEntity<MobileNumProxy> createMobile(@Valid @RequestBody MobileNumProxy proxy){
		return new ResponseEntity<>(services.create(proxy), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateMobile/{id}")
	public ResponseEntity<MobileNumProxy> updateMobileById(@Valid @PathVariable String id,@RequestBody MobileNumProxy proxy){
		return new ResponseEntity<>(services.updateById(proxy, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMobile/{id}")
	public ResponseEntity<ApiResponseMessage> deteleMobileById(@PathVariable String id){
		return new ResponseEntity<>(services.deleteById(id), HttpStatus.OK);
	}

}
