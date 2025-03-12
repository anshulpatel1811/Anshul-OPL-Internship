package com.security.learn;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.learn.entities.User;
import com.security.learn.repositories.UserRepository;

@SpringBootApplication
public class SecurityProjectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SecurityProjectApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user=userRepository.findByUsername("Anshul").orElse(null);
		
		if(user==null) {
			user= new User();
			user.setUserId(UUID.randomUUID().toString());
			user.setUsername("Anshul");
			user.setPassword(passwordEncoder.encode("dhruv"));
			user.setRole("USER");
			userRepository.save(user); 
		}
	}

}
