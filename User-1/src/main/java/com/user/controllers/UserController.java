package com.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dtos.ApiResponseMessage;
import com.user.dtos.UserDto;
import com.user.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create
	@PostMapping
	public ResponseEntity<UserDto> creatUser(@RequestBody UserDto userDto)
	{
		UserDto userDto1 = userService.createUser(userDto);
		return new ResponseEntity<UserDto>(userDto1, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") String userId,@RequestBody UserDto userDto)
	{
		UserDto updateUserDto = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updateUserDto, HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("id") String userId)
	{
		userService.deleteUser(userId);
		ApiResponseMessage message 
							= ApiResponseMessage
							.builder()
							.message("User is deleted Successfully !!")
							.success(true)
							.status(HttpStatus.OK)
							.build();
		
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	//get all
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> allUser = userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(allUser, HttpStatus.OK);
	}
	
	//get single
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId)
	{
		return new ResponseEntity<UserDto>(userService.getUserById(userId), HttpStatus.OK);
	}
	
	//get by email
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
	{
		return new ResponseEntity<UserDto>(userService.getUserByEmail(email), HttpStatus.OK);
	}
	
	//search user
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword)
	{
		return new ResponseEntity<List<UserDto>>(userService.searchUser(keyword), HttpStatus.OK);
	}
}

