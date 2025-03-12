package com.blood.bank.service_gateway.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceClient {
//	@Autowired
//	private WebClient.Builder webClientBuilder;

	private final WebClient webClient;

	// Inject the WebClient.Builder from Spring context
	public AuthServiceClient(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:1001").build(); // Set the base URL for your auth
																					// service
	}

	// Non-blocking token validation using WebClient
	public Mono<Boolean> validateToken(String token) {
		System.err.println("Validate token method executed.");
		return webClient.get().uri("/auth/validate-token").header("Authorization", "Bearer " + token).retrieve()
				.bodyToMono(Boolean.class); // Return Mono<Boolean> asynchronously
	}

	// Non-blocking login using WebClient
	public Mono<String> login(String username, String password) {
		return webClient.post().uri("/auth/login").bodyValue(new LoginRequest(username, password)) // Sending a request
																									// body (use your
																									// LoginRequest DTO
																									// if necessary)
				.retrieve().bodyToMono(String.class); // Return Mono<String> asynchronously
	}

	// Fetch roles for a given JWT token
	public Mono<String> getRolesFromToken(String token) {
		System.err.println("Control is at auth serviceclient/getrolesfromtoken.");
		return webClient.get().uri("/auth/roles") // Endpoint that returns the roles based on the JWT token
				.header("Authorization", "Bearer " + token) // Add token to the Authorization header
				.retrieve().bodyToMono(String.class); // Assuming roles are returned as a plain string (comma-separated,
														// etc.)
	}

	// Add a DTO for the login request if necessary
	public static class LoginRequest {
		private String username;
		private String password;

		public LoginRequest(String username, String password) {
			this.username = username;
			this.password = password;
		}

		// Getters and Setters
	}
}
