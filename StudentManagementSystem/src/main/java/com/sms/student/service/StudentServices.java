package com.sms.student.service;

import java.util.List;

import com.sms.student.proxy.StudentProxy;
import com.sms.student.util.ApiResponseMessage;

public interface StudentServices {

	// create
	StudentProxy create(StudentProxy proxy);
	
	// update by id
	StudentProxy updateById(StudentProxy proxy,String id);
	
	// delete by id 
	ApiResponseMessage deleteById(String id);
	
	// find by id
	StudentProxy findById(String id);
	
	// findAll
	List<StudentProxy> findAll();
	
	// delete by id
	ApiResponseMessage deleteAll();
}
