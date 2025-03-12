package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Student;

@Controller
@ResponseBody
@RequestMapping("/student")
public class StudentController {

	
	@GetMapping("/data")
	public Student getData1(
			@RequestParam String id,
			@RequestParam String name,
			@RequestParam String adress
	){
		Student s1 = new Student(id, name, adress);
		
		return s1;
		
	}
	
	@GetMapping("{id}/{name}/{address}")
	public Student getData(
			@PathVariable String id,
			@PathVariable String name,
			@PathVariable String address			
	){
		Student s = new Student();
		s.setId(id);
		s.setName(name);
		s.setAddress(address);
		
		return s;
		
	}
}
