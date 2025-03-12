package com.security.learn.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity(debug = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	
	//configure security
	
	@Bean
	public SecurityFilterChain securityfilterConfig(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(c->c.disable());
		
		httpSecurity.authorizeHttpRequests(
				request -> {
					request.requestMatchers("/api/route2").permitAll();
					request.requestMatchers("/api/route1").authenticated();
					request.requestMatchers("/users/**").permitAll();//regex
					request.requestMatchers(HttpMethod.POST,"/products").authenticated();
					request.requestMatchers(HttpMethod.POST,"/products").hasRole("ADMIN");
					request.anyRequest().authenticated();
				}
		);
		
		
//		
//		httpSecurity.authorizeHttpRequests(r -> r.requestMatchers("/api/route2").permitAll()
//				.requestMatchers("users/**").permitAll()
//				.requestMatchers(HttpMethod.POST,"/products").authenticated()
//				.anyRequest().authenticated()
//		);
		
		httpSecurity.formLogin(withDefaults());
		httpSecurity.httpBasic(withDefaults());
		return httpSecurity.build();
	}
	
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		// Creating user1
//		UserDetails user1 = User.builder()
//							.username("Anshul")
//							.password("dhruv")
//							.roles("ADMIN","GUEST")
//							.build();
//		
//		// Creating user2
//		UserDetails user2 = User.builder()
//				.username("Ram")
//				.password("sita")
//				.roles("ADMIN")
//				.build();
//		
//		// Creating user3
//		UserDetails user3 = User.withDefaultPasswordEncoder()
//				.username("Kush")
//				.password("sita")
//				.roles("ADMIN")
//				.build();		
//	
//		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2,user3);
//		return inMemoryUserDetailsManager;
//	}
	
	// we will use password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
