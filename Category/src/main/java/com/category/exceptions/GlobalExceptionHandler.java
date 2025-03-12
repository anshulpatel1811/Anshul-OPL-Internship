package com.category.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.category.dtos.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		ApiResponse message = ApiResponse.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handlerResourceNotFoundException(MethodArgumentNotValidException ex){
		
		Map<String, Object> response = new HashMap<>();
		List<ObjectError> allErrors = ex.getAllErrors();
		allErrors.stream().forEach(objectError ->{
			String message = objectError.getDefaultMessage();
			String field = ((FieldError)objectError).getField();
			response.put(field, message);
		});
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ListIsEmptyException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ListIsEmptyException ex){
		ApiResponse message = ApiResponse.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		ApiResponse message = ApiResponse.builder().message(ex.getMessage()).success(false).status(HttpStatus.CONFLICT).build();
		return new ResponseEntity<ApiResponse>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse> handlerDataIntegrityViolationException(DataIntegrityViolationException ex){
		ApiResponse message = ApiResponse.builder().message(ex.getMessage()).success(false).status(HttpStatus.CONFLICT).build();
		return new ResponseEntity<ApiResponse>(message, HttpStatus.CONFLICT);
	}

}
