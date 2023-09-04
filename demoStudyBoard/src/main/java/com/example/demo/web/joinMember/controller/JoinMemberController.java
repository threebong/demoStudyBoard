package com.example.demo.web.joinMember.controller;

import java.util.HashMap;

//import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.CustomUserDetails;
import com.example.demo.auth.JwtFilter;
import com.example.demo.auth.JwtProvider;
import com.example.demo.web.joinMember.service.JoinMemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

//import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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
		public ResponseEntity<Object> userLogin(@RequestBody HashMap<String, Object> request, HttpServletResponse response) throws Exception{
//		public String userLogin(@RequestBody HashMap<String, Object> request) throws Exception{
		System.out.println(":::::::::::::::LOGIN:::::::::::::::::");
		
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(request.get("user_id"), request.get("user_pw"));
		System.out.println(request);
	
		System.out.println(authenticationToken);
		
		//아래의 메소드로 실행시키면 JpaUserDetailService로 이동한다
		/*
		 * 1.AuthenticationManger의 구현체인 ProviderManager의 autnentication() 메소드 실행
		 * 2.해당 메소드에서 AuthenticationProvider인터페이스의 authenticate() 메소드 실행 
		 * 		해당 인터페이스는 UserDetailService(DB의 이용자 정보를 가져오는) 인터페이스 사용함
		 * 3. UserDetailService인터페이스의 loadUserByUsername()메소드 호출하게 됨
		 * 4. JpaUserDetailsService 구현체에 @Override된 loadUserByUsername() 메소드 호출함
		 * */
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		//유저정보세팅중ing
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(authentication.getName()); 
		System.out.println(authentication.getPrincipal());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(principal);
		CustomUserDetails userDetails = (CustomUserDetails)principal;
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getMember().getMemberName());
		System.out.println(userDetails.getMember().toString());
		//Token
		System.out.println("-------------토큰-----------------");
		String jwt = tokenProvider.createToken(authentication);
		System.out.println(jwt);
		//user 정보 가져오기?
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> user = new HashMap<String,String>();
		user = objectMapper.convertValue(userDetails.getMember(), HashMap.class);
		user.put("memberPw", "");
		user.put(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		System.out.println(user);
		
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		
//		Cookie cookie = new Cookie("accessToken", jwt);
//		cookie.setMaxAge(60*60*24);
//		cookie.setPath("/api/loginMember");
//		cookie.setHttpOnly(true);
		
//		response.addCookie(cookie);
//		ObjectMapper objectMapper = new ObjectMapper();
		
//		HashMap<String, String> token = new HashMap<>();
//		byte[] tokenStr = objectMapper.writeValueAsBytes(token);
//		response.getOutputStream().write(tokenStr);
//		response.addHeader(JwtFilter.AUTHORIZATION_HEADER, "Bearer " +jwt );
//		HttpHeaders httpheaders = new HttpHeaders();
//		httpheaders.add(JwtFilter.AUTHORIZATION_HEADER	,"Bearer " +  jwt);
		
//		return new ResponseEntity<Object>(authentication, httpheaders, HttpStatus.valueOf(200)); //생성자패턴 body,header,HttpStatus.valueOf(code)
		return ResponseEntity.ok()
//				.headers(httpheaders)
//				.body(authentication);
				.body(user);  //TODO 여기서부터
	}
}
