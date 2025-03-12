package com.bank_managementy.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank_managementy.entity.BankAccount;
import com.bank_managementy.services.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	private static List<BankAccount> b = new ArrayList<>();
	
	@Override
	public List<BankAccount> getAll() {
		return b;
	}

	@Override
	public BankAccount getById(String id) {
		BankAccount bankAccount = b.stream().filter(c->c.getAccountId().equals(id)).findFirst().get();
		return bankAccount;
	}

	@Override
	public Boolean deleteById(String id) {
		BankAccount bankAccount = b.stream().filter(c->c.getAccountId().equals(id)).findFirst().get();
		return b.remove(bankAccount);
	}

	@Override
	public Boolean updateById(String id, BankAccount ba) {
		BankAccount bankAccount = b.stream().filter(c->c.getAccountId().equals(id)).findFirst().get();
		bankAccount.setAccountId(ba.getAccountId());
		bankAccount.setAccountHolderName(ba.getAccountHolderName());
		bankAccount.setBalance(ba.getBalance());
		bankAccount.setAccountType(ba.getAccountType());
		return bankAccount.toString().isEmpty()?false:true;
	}

	@Override
	public BankAccount save(BankAccount ba) {
		b.addLast(ba);
		return b.contains(ba)?ba:null;
	}

	
}
