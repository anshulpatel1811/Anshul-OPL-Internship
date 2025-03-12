package com.blood.bank.service_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blood.bank.service_auth.domain.User;
import com.blood.bank.service_auth.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	// Endpoint for generating JWT token
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		String token = authService.generateToken(username);
		return ResponseEntity.ok("Bearer " + token); // Return the token in the response
	}

	// Endpoint for validating the JWT token
	@GetMapping("/validate-token")
	public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
		boolean isValid = authService.validateToken(token);
		return ResponseEntity.ok(isValid);
	}

	// New endpoint to save the complete user and forward based on role
	@PostMapping("/save-user")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		// Save user and forward to the respective service based on role
		String result = authService.saveUser(user);
		return ResponseEntity.ok(result);
	}
}
