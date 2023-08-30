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
	
	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private final TokenProvider tokenProvider;
	
	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	/*
	 * 실제 필터링 로직
	 * doFilter : Token 인증 정보를 SecurityContext에 저장
	 * */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{
		//resolveToken 통해 Token받아
		
		HttpServletRequest httpServeletRequest = (HttpServletRequest)request;
		String jwt = resolveToken(httpServeletRequest);
		String requestURI = httpServeletRequest.getRequestURI();
		
		//유효성 검증후 정상이면 SecurityContext에 저장
		if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			logger.debug(":::::::::: Security Context에 '{}' 인증 정보를 저장했습니다 :::::::::  , uri: {}", authentication.getName(), requestURI);
		} else {
			logger.debug("::::::::::::유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
		}
		
		//생성한 필터 실행
		chain.doFilter(httpServeletRequest, response);
		
	}
	
	//Token꺼내오기
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			System.out.println("token :::::      " + bearerToken);
			return bearerToken.substring(7);
		}
		return null;
		
	}
	
	
}
