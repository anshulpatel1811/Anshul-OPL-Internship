package com.category.controllers;

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

import com.category.dtos.ApiResponse;
import com.category.dtos.CategoryDto;
import com.category.services.CategorySevice;

import jakarta.validation.Valid;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategorySevice sevice;

	@GetMapping
	public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto){
		
		return new ResponseEntity<CategoryDto>(sevice.create(categoryDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<CategoryDto> findById(@PathVariable Long id){
		
		return new ResponseEntity<CategoryDto>(sevice.findById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<List<CategoryDto>> findAll(){
		
		return new ResponseEntity<>(sevice.findAll(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
		
		return new ResponseEntity<>(sevice.deleteById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<ApiResponse> deleteAll(){
		
		return new ResponseEntity<>(sevice.deleteAll(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> update(@PathVariable Long id,@Valid @RequestBody CategoryDto categoryDto){
		
		return new ResponseEntity<CategoryDto>(sevice.update(id, categoryDto),HttpStatus.OK);
	}
	
//	@PostMapping("/{keyword}")
//	public ResponseEntity<List<CategoryDto>> findByKeyword(@PathVariable String keyword){
//		
//		return new ResponseEntity<>(sevice.findByKeyword(keyword),HttpStatus.OK);
//	}
	
	

}
