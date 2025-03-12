package com.emp.security.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emp.security.domain.Employee;
import com.emp.security.repositories.EmpRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private EmpRepository empRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee employee = empRepository.findByEmpName(username).orElseThrow(()-> new RuntimeException("Data Not Found With Given Input !!"));
		
		return new MyUserDetails(employee);
	}

}
