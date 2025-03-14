package com.blood.bank.service_registry.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(c -> c.disable());
        httpSecurity.authorizeHttpRequests(request -> {
//           request.requestMatchers("/eureka/**").permitAll();
        	request.requestMatchers("/actuator/**").permitAll();
            request.anyRequest().authenticated();
        });
       
        httpSecurity.formLogin(withDefaults());
        httpSecurity.httpBasic(withDefaults());
        return httpSecurity.build();
    }
    
    @Bean
     UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("ADMIN")
            .password("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }

}
