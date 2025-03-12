package com.security.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.security.learn.domain.Student;
import com.security.learn.repositories.StudentRepository;
import com.security.learn.security.JwtHelper;

@SpringBootApplication
@ComponentScan(basePackages = "com.security.learn")
public class SecurityProject1Application {

	@Autowired
	private static StudentRepository repository;
	
	@Autowired
	private static JwtHelper helper;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityProject1Application.class, args);
		
		System.out.println("Testing jwt tokens");
		
		Student student = repository.findById(1l).get();
		System.out.println(student);
		String token = helper.generateToken(student);
		System.out.println(token);
	}
	
	
}