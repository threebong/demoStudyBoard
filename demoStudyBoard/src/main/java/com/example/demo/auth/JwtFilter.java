package com.example.demo.auth;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
/*
 * JWT를 위한 커스텀 필터
 * doFilter를 오버라이딩하여 토큰의 인증정보 --> Security Context에 저장함
 * 
 * 
 * */
public class JwtFilter extends GenericFilterBean{
	
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private final JwtProvider tokenProvider;
	
	public JwtFilter(JwtProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	/*
	 * 실제 필터링 로직
	 * doFilter : Token 인증 정보를 SecurityContext에 저장
	 * */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{
		
		
		System.out.println("::::::::JWT TOKEN PROVIDER DOFILTER:::::::::::");
		
		//resoloveToken통해서 토큰 받아옴 
		HttpServletRequest httpServeletRequest = (HttpServletRequest)request;
		String jwt = resolveToken(httpServeletRequest);
		String requestURI = httpServeletRequest.getRequestURI();
		
		System.out.println("토큰값이있는가");
		System.out.println(jwt);
		System.out.println(httpServeletRequest.getHeader(AUTHORIZATION_HEADER));
		
		//유효성 검증후 정상이면 SecurityContext에 저장
		if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			logger.info(":::::::::: Security Context에 '{}' 인증 정보를 저장했습니다 :::::::::  , uri: {}", authentication.getName(), requestURI);
		} else {
			logger.info("::::::::::::유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
		}
		
		//생성한 필터 실행
		chain.doFilter(httpServeletRequest, response);
		
	}
	
	//Request Header에서 Token꺼내오기
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			System.out.println("꺼내온 token :::::      " + bearerToken);
			return bearerToken.substring(7);
		}else {
			//test
			System.out.println("꺼내온 TOKEN 없음!!!!!!!!");
		}
		return null;
		
	}
	
	
}
