package com.sms.student.service;

import java.util.List;

import com.sms.student.proxy.BookProxy;
import com.sms.student.util.ApiResponseMessage;

public interface BookServices {

		// create
		BookProxy create(BookProxy proxy);
		
		// update by id
		BookProxy updateById(BookProxy proxy,String id);
		
		// delete by id 
		ApiResponseMessage deleteById(String id);
		
		// find by id
		BookProxy findById(String id);
		
		// findAll
		List<BookProxy> findAll();
		
		// delete by id
		ApiResponseMessage deleteAll();
}
