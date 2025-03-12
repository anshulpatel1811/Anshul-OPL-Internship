package com.emp.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emp.security.services.impl.MyUserDetailsService;
import com.emp.security.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private MyUserDetailsService detailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try{

			String jwtToken = jwtUtils.getTokenFromRequest(request);
			if(jwtToken!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				String userName = jwtUtils.extractUserName(jwtToken);
				
				UserDetails userDetails = detailsService.loadUserByUsername(userName);
			
				if(userDetails!=null && jwtUtils.validateToken(jwtToken, userDetails)) {
					
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		filterChain.doFilter(request, response);
	}

}
