package com.security.learn.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.learn.proxy.JwtRequest;
import com.security.learn.proxy.JwtResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@PostMapping("/genrate-token")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		return ResponseEntity.ok(null);
	}
}
