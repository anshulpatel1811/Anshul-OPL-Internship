package com.ems.employee.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ems.employee.entity.EmployeeProfileImage;
import com.ems.employee.proxy.EmployeeProfileImageProxy;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Mapper {

	@Autowired
	private ObjectMapper mapper;

	public EmployeeProfileImage ProxyToEntity(EmployeeProfileImageProxy proxy) {
		return mapper.convertValue(proxy, EmployeeProfileImage.class);
	}
	
	public EmployeeProfileImageProxy EntityToProxy(EmployeeProfileImage proxy) {
		return mapper.convertValue(proxy, EmployeeProfileImageProxy.class);
	}
}
