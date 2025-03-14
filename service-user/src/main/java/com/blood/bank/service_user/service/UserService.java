package com.blood.bank.service_user.service;

import java.util.List;

import com.blood.bank.service_user.proxy.UserProxy;

public interface UserService {

	String deleteUser(String username);

	String searchBloodGroupDetails(String bloodGroup);
	
	String orderBloodInUnit(String bloodGroupId);

	List<UserProxy> getAllUsers();
	
	UserProxy getUserByUsername(String username);

	String deleteUserByUsername(String username);
	
	UserProxy saveAdmin(UserProxy userProxy);
}
