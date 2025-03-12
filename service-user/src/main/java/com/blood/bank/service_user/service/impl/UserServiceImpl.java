package com.blood.bank.service_user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blood.bank.service_user.domain.User;
import com.blood.bank.service_user.proxy.UserProxy;
import com.blood.bank.service_user.repository.UserRepository;
import com.blood.bank.service_user.service.UserService;
import com.blood.bank.service_user.utils.MapperUtile;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String deleteUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchBloodGroupDetails(String bloodGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orderBloodInUnit(String bloodGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserProxy> getAllUsers() {
		List<User> list = userRepository.findAll();
		return MapperUtile.convertListOfValue(list, UserProxy.class);
	}

	@Override
	public UserProxy getUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User Not Found with given Input !!"));
		return MapperUtile.convertValue(user, UserProxy.class);
	}

	@Override
	public String deleteUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProxy saveAdmin(UserProxy userProxy) {
		// TODO Auto-generated method stub
		return null;
	}

}
