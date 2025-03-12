package com.emp.security.exception;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emp.security.utils.ApiResponseMessage;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> handlerResourceNotFoundException(ResourceNotFoundException ex){
		ApiResponseMessage message = ApiResponseMessage.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ListIsEmptyException.class)
	public ResponseEntity<ApiResponseMessage> handlerResourceNotFoundException(ListIsEmptyException ex){
		ApiResponseMessage message = ApiResponseMessage.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadApiRequest.class)
	public ResponseEntity<ApiResponseMessage> handlerResourceNotFoundException(BadApiRequest ex){
		ApiResponseMessage message = ApiResponseMessage.builder().message(ex.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InsufficientAuthenticationException.class)
	public Map<String, Object> exception(HttpServletRequest req, HttpServletResponse res,
			InsufficientAuthenticationException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("Error-Message: ", e.getMessage());
		map.put("Error-Path: ", req.getServletPath());
		map.put("Error-Class: ", e.getClass());
		map.put("Error-Status_code: ", res.getStatus());
		return map;
	}
	
	@ExceptionHandler(value = MalformedJwtException.class)
	public Map<String, Object> exception(HttpServletRequest req, HttpServletResponse res, MalformedJwtException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("Error-Message: ", e.getMessage());
		map.put("Error-Path: ", req.getServletPath());
		map.put("Error-Class: ", e.getClass());
		map.put("Error-Status_code: ", res.getStatus());
		return map;
	}
	
	@ExceptionHandler(value = SignatureException.class)
	public Map<String, Object> exception(HttpServletRequest req, HttpServletResponse res, SignatureException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("Error-Message: ", e.getMessage());
		map.put("Error-Path: ", req.getServletPath());
		map.put("Error-Class: ", e.getClass());
		map.put("Error-Status_code: ", res.getStatus());
		return map;
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
 
	@ExceptionHandler(value = Exception.class)
	public Map<String, Object> exception(HttpServletRequest req, HttpServletResponse res, Exception e) {
		Map<String, Object> map = new HashMap<>();
		map.put("Error-Message: ", e.getMessage());
		map.put("Error-Path: ", req.getServletPath());
		map.put("Error-Class: ", e.getClass());
		map.put("Error-Status_code: ", res.getStatus());
		return map;
	}
}
