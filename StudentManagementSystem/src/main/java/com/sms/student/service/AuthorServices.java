package com.sms.student.service;

import java.util.List;

import com.sms.student.proxy.AuthorProxy;
import com.sms.student.util.ApiResponseMessage;

public interface AuthorServices {

		// create
		AuthorProxy create(AuthorProxy proxy);
		
		// update by id
		AuthorProxy updateById(AuthorProxy proxy,String id);
		
		// delete by id 
		ApiResponseMessage deleteById(String id);
		
		// find by id
		AuthorProxy findById(String id);
		
		// findAll
		List<AuthorProxy> findAll();
		
		// delete by id
		ApiResponseMessage deleteAll();
}
