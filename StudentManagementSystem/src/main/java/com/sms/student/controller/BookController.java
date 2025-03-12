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

import com.sms.student.proxy.BookProxy;
import com.sms.student.service.BookServices;
import com.sms.student.util.ApiResponseMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookServices services;
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<BookProxy>> findAllBook(){
		
		return new ResponseEntity<>(services.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookProxy> findById(@PathVariable String id){
		
		return new ResponseEntity<>(services.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/saveBook")
	public ResponseEntity<BookProxy> createBook(@Valid @RequestBody BookProxy proxy){
		return new ResponseEntity<>(services.create(proxy), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<BookProxy> updateBookById(@Valid @PathVariable String id,@RequestBody BookProxy proxy){
		return new ResponseEntity<>(services.updateById(proxy, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<ApiResponseMessage> deteleBookById(@PathVariable String id){
		return new ResponseEntity<>(services.deleteById(id), HttpStatus.OK);
	}

}
