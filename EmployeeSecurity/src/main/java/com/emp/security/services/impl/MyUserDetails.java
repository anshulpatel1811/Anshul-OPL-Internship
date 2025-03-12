package com.emp.security.services.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.emp.security.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyUserDetails implements UserDetails{

	private Employee employee;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority(employee.getEmpRole()));
	}

	@Override
	public String getPassword() {
		
		return employee.getEmpPassword();
	}

	@Override
	public String getUsername() {
		
		return employee.getEmpName();
	}

	@Override
	public boolean isEnabled() {
		
		return employee.getIsActive();
	}

}
