package com.blood.bank.service_gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.blood.bank.service_gateway.services.AuthServiceClient;

@Component("jwtGatewayFilterCustom")
public class JwtGatewayFilterCustom extends AbstractGatewayFilterFactory<JwtGatewayFilterCustom.Config> {

	private final AuthServiceClient authServiceClient;

	public JwtGatewayFilterCustom(AuthServiceClient authServiceClient) {
		super(Config.class);
		this.authServiceClient = authServiceClient;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
			System.err.println(authHeader);

			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				String jwtToken = authHeader.substring(7);
				System.err.println(jwtToken);
				// Make a call to AuthService to extract roles from the token
				return authServiceClient.getRolesFromToken(jwtToken).flatMap(roles -> {
					System.err.println("First");
					System.err.println(roles);
					if (hasAccessBasedOnRoles(roles, config.getRequiredRoles())) {
						System.err.println("second");
						String path = exchange.getRequest().getURI().getPath();
						System.err.println(path);
						System.err.println(roles);
						// Add the Authorization header and continue the chain if roles are valid
						if (path.startsWith("/Donor") && roles.equals("DONOR")) {
							exchange.getRequest().mutate().header("Authorization", "Bearer " + jwtToken).build();
							return chain.filter(exchange);
						}

						else if (path.startsWith("/admin") && roles.equals("ADMIN")) {
							exchange.getRequest().mutate().header("Authorization", "Bearer " + jwtToken).build();
							return chain.filter(exchange);
						}

						else if (path.startsWith("/User") && roles.equals("USER")) {
							exchange.getRequest().mutate().header("Authorization", "Bearer " + jwtToken).build();
							return chain.filter(exchange);
						}
						throw new RuntimeException("Unauthorized access!!");
					} else {
						// Forbidden if roles don't match
						System.err.println("Third");
						exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
						return exchange.getResponse().setComplete();
					}
//					return chain.filter(exchange);
				});
//				}).switchIfEmpty(Mono.defer(() -> {
//					System.err.println("Fourth");
//					exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); // Token invalid or missing
//					return exchange.getResponse().setComplete();
//				}))

			} else {
				System.err.println("Fifth");
				// Unauthorized if Authorization header is missing or malformed
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
		};
	}

	// Check if the user's roles match the required roles for access
	private boolean hasAccessBasedOnRoles(String userRoles, String[] requiredRoles) {
//		System.err.println(requiredRoles.toString());
//		requiredRoles = userRoles.split(",");
		System.err.println(requiredRoles.toString());

		if (userRoles != null) {
			for (String role : requiredRoles) {
				if (userRoles.contains(role)) {
					return true;
				}
			}
		}
		return false;
	}

	public String getName() {
		return "JwtGatewayFilter";
	}

	public static class Config {
		private String[] requiredRoles = { "ADMIN", "DONOR", "USER" }; // Define required roles for access

		public String[] getRequiredRoles() {
			return requiredRoles;
		}

		public void setRequiredRoles(String[] requiredRoles) {
			this.requiredRoles = requiredRoles;
		}
	}
}
