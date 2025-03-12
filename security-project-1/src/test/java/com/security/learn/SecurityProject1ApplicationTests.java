package com.security.learn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.security.learn.domain.Student;
import com.security.learn.repositories.StudentRepository;
import com.security.learn.security.JwtHelper;

@SpringBootTest
class SecurityProject1ApplicationTests {

	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private JwtHelper helper;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testToken() {
		
		System.out.println("Testing jwt tokens");
		
		Student student = repository.findByName("Anshul").get();
		System.out.println(student);
		String token = helper.generateToken(student);
		System.out.println(token);
	}
}
