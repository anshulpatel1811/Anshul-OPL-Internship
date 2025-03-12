package com.user.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dtos.UserDto;
import com.user.entity.User;
import com.user.repositories.UserRepository;
import com.user.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		//generate unique id in string format
		String userId = UUID.randomUUID().toString();
		userDto.setUserId(userId);
		
		//dto->entity
		User user = mapper.map(userDto, User.class);
		User savedUser = userRepository.save(user);
		
		//entity->dto
		UserDto newDto = mapper.map(savedUser, UserDto.class);
		
		return newDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with given id"));
		user.setName(userDto.getName());
		// email update
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());
		user.setPassword(userDto.getPassword());
		user.setImageName(userDto.getImageName());
		
		//save data
		User updatedUser = userRepository.save(user);
		UserDto updatedDto = mapper.map(updatedUser, UserDto.class);
		
		return updatedDto;
	}

	@Override
	public void deleteUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with given id"));
		
		//delete user
		userRepository.delete(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
		List<User> users = userRepository.findAll();
		List<UserDto> dtoList = users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public UserDto getUserById(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with given id"));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		
		User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found with given email id"));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> searchUser(String keyword) {
		
		List<User> users = userRepository.findByNameContaining(keyword);
		List<UserDto> dtoList = users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
		return dtoList;
	}

}
