package com.example.demo.web.joinMember.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtFilter;
import com.example.demo.auth.JwtProvider;
import com.example.demo.web.joinMember.service.JoinMemberService;

@RestController
@RequestMapping("/api")
public class JoinMemberController {
	
	@Autowired
	private JoinMemberService joinMemberService;
	
	private final JwtProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	public JoinMemberController(JwtProvider tokenProvider, AuthenticationManagerBuilder builder) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = builder;
	}
	/*
	 * 회원가입 
	 * */
	@PostMapping("/joinMember")
	public int joinMember(@RequestBody HashMap<String, Object> request) throws Exception{
		int result = 0;
		
		try {
			result = joinMemberService.joinMember(request);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/*
	 * 중복 ID확인
	 * */
	@GetMapping("/checkDupId")
	public int checkDupId(@RequestParam HashMap<String, Object> request) throws Exception {
		System.out.println("-->중복ID확인");
//		joinMemberService.selectOneMemberId((String)request.get("memberId"));
		return joinMemberService.selectOneMemberId((String)request.get("memberId"));
	}
	
	/*
	 * 로그인
	 * */
	@PostMapping("/loginMember")
//	public String userLogin(@RequestBody HashMap<String, Object> request) throws Exception{
		public String userLogin(@RequestBody HashMap<String, Object> request) throws Exception{
		System.out.println(":::::::::::::::LOGIN:::::::::::::::::");
		
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(request.get("user_id"), request.get("user_pw"));
		System.out.println(request);
		System.out.println((String)request.get("user_pw"));
	
		System.out.println(authenticationToken);
		
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//사용자정보
		System.out.println("-------------사용자 정보-----------------");
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String jwt = tokenProvider.createToken(authentication);
		System.out.println(jwt);
		
		HttpHeaders httpheaders = new HttpHeaders();
		httpheaders.add(JwtFilter.AUTHORIZATION_HEADER	,"Bearer " +  jwt);
		return jwt;
	}
}
