package com.security.learn.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.learn.domain.Role;
import com.security.learn.repositories.RoleRepository;
import com.security.learn.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository repository;
	
	@Override
	public void saveRole(Role role) {
		
		Role role1 = repository.findByName("Role_Teacher").orElse(null);
		Role role2 = repository.findByName("Role_Student").orElse(null);
		
		if(role1==null) {
			
			role1 = new Role();
			role1.setName("Role_Teacher");
			role1.setRoleId(1l);
			
			repository.save(role1);
		}
		
		if(role2==null) {
			
			role2 = new Role();
			role2.setName("Role_Student");
			role2.setRoleId(2l);
			
			repository.save(role2);
		}
		
	}

}
