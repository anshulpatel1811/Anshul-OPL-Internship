package com.user.services;

import java.util.List;

import com.user.Entity.User;

public interface UserService {

	// create
	User saveUser(User user);
	
	// update by id
	User updateUser(String userId,User user);
	
	// delete by id
	void deleteUser(String userId);
	
	// get user by id
	User getUserById(String userId);
	
	// get All
	List<User> getAllUsers();
}
