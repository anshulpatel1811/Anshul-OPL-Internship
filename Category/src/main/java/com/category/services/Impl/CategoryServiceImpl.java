package com.category.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.category.dtos.ApiResponse;
import com.category.dtos.CategoryDto;
import com.category.entities.Category;
import com.category.exceptions.ListIsEmptyException;
import com.category.exceptions.ResourceNotFoundException;
import com.category.repositories.CategoryRepositories;
import com.category.services.CategorySevice;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoryServiceImpl implements CategorySevice{

	@Autowired
	private CategoryRepositories categoryRepositories;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public CategoryDto create(CategoryDto categoryDto) {
		
		Category category = mapper.convertValue(categoryDto, Category.class);
		categoryRepositories.save(category);
		
		return mapper.convertValue(category, CategoryDto.class);
	}

	@Override
	public CategoryDto findById(Long id) {
		
		Category category = categoryRepositories.findById(id).orElseThrow(()->new ResourceNotFoundException("Data not found with this id"));
		return mapper.convertValue(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> findAll() {
		
		List<Category> list = categoryRepositories.findAll();
		
		if(list.isEmpty()) {
			throw new ListIsEmptyException();
		}
		
		List<CategoryDto> list2 = list.stream().map(obj-> mapper.convertValue(obj, CategoryDto.class)).collect(Collectors.toList());
		return list2;
	}

	@Override
	public ApiResponse deleteById(Long id) {
		Category category = categoryRepositories.findById(id).orElseThrow(()->new ResourceNotFoundException("Data not found with this id"));
		
		categoryRepositories.delete(category);
		
		ApiResponse response = ApiResponse.builder().message("Category is deleted successfully !!").success(true).status(HttpStatus.OK).build();
		return response;
	}

	@Override
	public ApiResponse deleteAll() {
		categoryRepositories.deleteAll();
		ApiResponse response = ApiResponse.builder().message("All Category is deleted successfully !!").success(true).status(HttpStatus.OK).build();
		return response;
	}

	@Override
	public CategoryDto update(Long id, CategoryDto categoryDto) {
		Category category = categoryRepositories.findById(id).orElseThrow(()->new ResourceNotFoundException("Data not found with this id"));
		
		
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		category.setPrice(categoryDto.getPrice());
		category.setStockQuantity(categoryDto.getStockQuantity());
		categoryRepositories.save(category);
		return mapper.convertValue(category, CategoryDto.class);
	}

//	@Override
//	public List<CategoryDto> findByKeyword(String keyword) {
//		List<Category> list = categoryRepositories.findByNameContaining(keyword);
//		List<CategoryDto> list2 = list.stream().map(obj-> mapper.convertValue(obj, CategoryDto.class)).collect(Collectors.toList());
//		return list2;
//	}

}
