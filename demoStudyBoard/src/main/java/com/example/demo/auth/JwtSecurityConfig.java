package com.example.demo.auth;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * TokenProvider, JwtFilter를 SecurityConfig에 적용할 때 사용 
 * JwtFilter를 필터 실행 이전에 실행되도록 설정함
 * */
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain	, HttpSecurity>{
	private final JwtProvider tokenProvider;
	
	//Token Provider 주입
	public JwtSecurityConfig(JwtProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	public void configure(HttpSecurity http) {
		System.out.println(":::::::::::CONFIGURE::::::::::::::");
		
		JwtFilter customFilter = new JwtFilter(tokenProvider);
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
