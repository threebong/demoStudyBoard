package com.example.demo.web.joinMember.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtProvider;
import com.example.demo.web.joinMember.service.JoinMemberService;

import jakarta.servlet.http.HttpServletRequest;

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
	@PostMapping("/login")
	public int userLogin(@RequestBody HashMap<String, Object> request) throws Exception{
//		public int userLogin(@RequestBody HashMap<String, Object> request) throws Exception{
		System.out.println(":::::::::::::::LOGIN:::::::::::::::::");
		
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(request, request);
		
		System.out.println(request);
		return 1;
	}
}
