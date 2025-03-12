package com.sms.student.service;

import java.util.List;

import com.sms.student.proxy.MobileNumProxy;
import com.sms.student.util.ApiResponseMessage;

public interface MobileNumServices {

			// create
			MobileNumProxy create(MobileNumProxy proxy);
			
			// update by id
			MobileNumProxy updateById(MobileNumProxy proxy,String id);
			
			// delete by id 
			ApiResponseMessage deleteById(String id);
			
			// find by id
			MobileNumProxy findById(String id);
			
			// findAll
			List<MobileNumProxy> findAll();
			
			// delete by id
			ApiResponseMessage deleteAll();
}
