package com.sms.student.service;

import java.util.List;

import com.sms.student.proxy.BranchProxy;
import com.sms.student.util.ApiResponseMessage;

public interface BranchServices {

		// create
		BranchProxy create(BranchProxy proxy);
		
		// update by id
		BranchProxy updateById(BranchProxy proxy,String id);
		
		// delete by id 
		ApiResponseMessage deleteById(String id);
		
		// find by id
		BranchProxy findById(String id);
		
		// findAll
		List<BranchProxy> findAll();
		
		// delete by id
		ApiResponseMessage deleteAll();
}
