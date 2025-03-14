package com.security.learn.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	private Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// api se pahle chalega jwt header : usko varify karne ke liye
		
		//Authorization : Bearer hkagfhjaghjgfhg
		String requestHeader = request.getHeader("Authorization");
		logger.info("Hearder {}",requestHeader);
		
		String username=null;
		
		String token=null;
		
		if(requestHeader!=null && requestHeader.startsWith("Bearer")) {
			// All ok then process...
			
			//get token from request hearder
			token=requestHeader.substring(7);
			
			try {
				
				username = jwtHelper.getUsernameFromToken(token);
				logger.info("Username {}",username);
				
			}catch (IllegalArgumentException ex) {
				logger.info("Illegal Argument while fetching the username !! "+ex.getMessage());
			}catch (ExpiredJwtException ex) {
				logger.info("Given jwt is expired !! "+ex.getMessage());
			}catch (MalformedJwtException ex) {
				logger.info("Some changed has done in token !! "+ex.getMessage());
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				if(username.equals(userDetails.getUsername()) && !jwtHelper.isTokenExpired(token)) {
					
					//token validation
					//then inside security context set authetication 
					
					UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				
			}
			
			filterChain.doFilter(request, response);
		}else {
			logger.info("Invalid Hearder !! Header is not start with Bearer !!");
		}
	}

}
