package com.user.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.Entity.User;
import com.user.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static List<User> users = new ArrayList<>();
	
	static {
		
	}
	
	@Override
	public User saveUser(User user) {
		
		users.add(user);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		
		users.removeIf(id -> id.getUserId().equals(userId));
	}

	@Override
	public User getUserById(String userId) {
	
		return users.stream().filter(id -> id.getUserId().equals(userId)).findFirst().get();
	}

	@Override
	public List<User> getAllUsers() {
		
		return users;
	}

	@Override
	public User updateUser(String userId, User user) {
		
		User user2 = users.stream().filter(id -> id.getUserId().equals(userId)).findFirst().get();
		user2.setUserId(user.getUserId());
		user2.setName(user.getName());
		user2.setPassword(user.getPassword());
		user2.setAbout(user.getAbout());
		user2.setImageName(user.getImageName());
		user2.setGender(user.getGender());
		user2.setEmail(user.getEmail());
		return user2;
	}

}
