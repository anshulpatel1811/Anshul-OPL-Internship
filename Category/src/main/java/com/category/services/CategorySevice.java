package com.category.services;

import java.util.List;

import com.category.dtos.ApiResponse;
import com.category.dtos.CategoryDto;

public interface CategorySevice {

	// create 
	CategoryDto create(CategoryDto categoryDto);
	
	// get by id
	CategoryDto findById(Long id);
	
	// get all
	List<CategoryDto> findAll();
	
	// delete by id
	ApiResponse deleteById(Long id);
	
	// delete all
	ApiResponse deleteAll();
	
	// update
	CategoryDto update(Long id,CategoryDto categoryDto);
	
//	// get by keyword
//	List<CategoryDto> findByKeyword(String keyword);
	
}
