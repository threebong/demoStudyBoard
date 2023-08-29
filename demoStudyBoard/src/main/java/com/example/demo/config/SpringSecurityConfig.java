package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerJwtAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import io.netty.handler.codec.http.cors.CorsConfig;
import jakarta.servlet.DispatcherType;

@Configuration
//@EnableWebSecurity  //이 어노테이션 안에 Configuration이 포함되어있다함 > 근데사용하면에러남
public class SpringSecurityConfig {
	
	private OAuth2AuthorizationServerJwtAutoConfiguration jwt;
	
	/*
	 * cors관련(?)
	 * */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
//			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
//			.addFilter(CorsConfigFilter)
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//					.requestMatchers("/","/login")
					.requestMatchers(new AntPathRequestMatcher("/api/login")
							, new AntPathRequestMatcher("/")
							, new AntPathRequestMatcher("/**") //<---- 나중에없애야함..
							)
					.permitAll()//얘네는 허용
					.anyRequest().authenticated() //어떠한 요청이라도 인증 필요
			)
//			.formLogin(login -> login //form 방식 로그인
//					.loginPage("/login") //커스텀 로그인 페이지 지정
//					.loginProcessingUrl("/api/login")// 로그인을 처리할 url? 
//					.usernameParameter("memberId")		// 로그인 시 form에서 가져올 값(id, email 등이 해당)
//	                .passwordParameter("memberPw")		// 로그인 시 form에서 가져올 값 - form id값
//					/*
//					 *  https://lotuus.tistory.com/78 참고해서변경
//					 * */
//					.defaultSuccessUrl("/") //성공시 dashboard
//					.permitAll() //대시보드 이동이 막히면 안되니까 허용
//			)
			.formLogin(login -> login.disable()) //초기로그인화면사라진다
			.logout(logout -> logout
					.deleteCookies("remove")
					.invalidateHttpSession(false)
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
					)
			;
		
		return http.build();
	}
	
	//PassEncoder interface구현체 BCrypt라고 명시 
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	
	
	
	
}
