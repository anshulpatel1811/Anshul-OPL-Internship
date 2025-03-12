package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmpController {

	@GetMapping("/home")
	public String getData() {
		return "home";
	}
	
	@GetMapping("/car")
	public String getData1() {
		return "car";
	}
}
