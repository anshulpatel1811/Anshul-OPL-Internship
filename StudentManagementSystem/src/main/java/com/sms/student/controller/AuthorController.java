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

import com.sms.student.proxy.AuthorProxy;
import com.sms.student.service.AuthorServices;
import com.sms.student.util.ApiResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("author")
public class AuthorController {

	@Autowired
	private AuthorServices services;
	
	@GetMapping("/getAllAuthors")
	public ResponseEntity<List<AuthorProxy>> findAllAuthor(){
		
		return new ResponseEntity<>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorProxy> findById(@PathVariable String id){
		
		return new ResponseEntity<>(services.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/saveAuthor")
	public ResponseEntity<AuthorProxy> createAuthor(@Valid @RequestBody AuthorProxy proxy){
		return new ResponseEntity<>(services.create(proxy), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateAuthor/{id}")
	public ResponseEntity<AuthorProxy> updateAuthorById(@Valid @PathVariable String id,@RequestBody AuthorProxy proxy){
		return new ResponseEntity<>(services.updateById(proxy, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAuthor/{id}")
	public ResponseEntity<ApiResponseMessage> deteleAuthorById(@PathVariable String id){
		return new ResponseEntity<>(services.deleteById(id), HttpStatus.OK);
	}

}
