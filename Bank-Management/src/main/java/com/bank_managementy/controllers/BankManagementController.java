package com.bank_managementy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank_managementy.entity.ApiResponseMessage;
import com.bank_managementy.entity.BankAccount;
import com.bank_managementy.services.BankAccountService;

@RestController
@RequestMapping("bank")
public class BankManagementController {

	@Autowired
	private BankAccountService service;
	
	@PostMapping("/getAll")
	public ResponseEntity<List<BankAccount>> getAll(){
		return new ResponseEntity<List<BankAccount>>(service.getAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BankAccount> save(@RequestBody BankAccount ba){
		return new ResponseEntity<BankAccount>(service.save(ba), HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<BankAccount> getbankAcount(@PathVariable String id){
		return new ResponseEntity<BankAccount>(service.getById(id), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> delete(@PathVariable String id){
		Boolean deleteById = service.deleteById(id);
		ApiResponseMessage message;
		if(deleteById==true) {
			message = ApiResponseMessage.builder().message("User is deleted Successfully !!").success(true).status(HttpStatus.OK).build();
		}else {
			message = ApiResponseMessage.builder().message("User is not deleted Successfully !!").success(false).status(HttpStatus.NOT_MODIFIED).build();
		}
		return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> update(@PathVariable String id,@RequestBody BankAccount ba){
		Boolean updateById = service.updateById(id, ba);
		ApiResponseMessage message;
		if(updateById==true) {
			message = ApiResponseMessage.builder().message("User is updated Successfully !!").success(true).status(HttpStatus.OK).build();
		}else {
			message = ApiResponseMessage.builder().message("User is not updated Successfully !!").success(false).status(HttpStatus.NOT_MODIFIED).build();
		}
		return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
	}
}
