package com.bank_managementy.services;

import java.util.List;

import com.bank_managementy.entity.BankAccount;

public interface BankAccountService {

	// get all
	List<BankAccount> getAll();
	
	// get by id
	BankAccount getById(String id);
	
	// delete by id
	Boolean deleteById(String id);
	
	// update by id
	Boolean updateById(String id,BankAccount b);
	
	// create account
	BankAccount save(BankAccount b);
}
