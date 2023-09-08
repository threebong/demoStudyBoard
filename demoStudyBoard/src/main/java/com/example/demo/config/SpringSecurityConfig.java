package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerJwtAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.auth.JwtSecurityConfig;
import com.example.demo.auth.JwtProvider;
import com.example.demo.auth.err.JwtAccessDeniedHandler;
import com.example.demo.auth.err.JwtAuthenticationEntryPoint;

import io.netty.handler.codec.http.cors.CorsConfig;
import jakarta.servlet.DispatcherType;
/*
 * Spring Security에서 가장 중요한 Spring Config클래스
 * WebSecurityConfigurerAdaptor가 Depreted됨 --> SecurityFilterChain직접 구현하여 @Bean 구현하는 방식 
 * */
@Configuration
@EnableWebSecurity  //이 어노테이션 안에 Configuration이 포함되어있다함 , Spring Security 활성화
@EnableMethodSecurity //@PreAuthorize어노테이션 메소드 단위로 추가하기 위해..(default: true)
public class SpringSecurityConfig {
	
	private final JwtProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	
	// TokenProvider,JwtAuthenticationEntryPoint,JwtAccessDeniedHandler 의존성 주입
    public SpringSecurityConfig(
            JwtProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ){
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }
	
    
    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/*
	 * cors관련(?)
	 * */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
//			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable()) //토큰 사용하므로 비활성화 
//			.addFilter(CorsConfigFilter)
			
			//예외 처리시 직접 만들었던 클래스 추가
			.exceptionHandling(exp -> exp.authenticationEntryPoint(jwtAuthenticationEntryPoint)
														.accessDeniedHandler(jwtAccessDeniedHandler))
			//세션 사용 안함, 세션 설정 STATELES로 함
			.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//					.requestMatchers("/","/login")
					.requestMatchers(new AntPathRequestMatcher("/api/loginMember")//로그인
							, new AntPathRequestMatcher("/")
							, new AntPathRequestMatcher("/api/checkDupId") //중복id체크
							, new AntPathRequestMatcher("/api/joinMember")//회원가입
							
//							, new AntPathRequestMatcher("/**") //<---- 나중에없애야함..
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
//			.logout(logout -> logout
//					.deleteCookies("remove")
//					.invalidateHttpSession(false)
//					.logoutUrl("/logout")
//					.logoutSuccessUrl("/login")
//					)
			
			//JwtFilter를 addFilterBefore로 등록했던 jwtSecurityConfig클래스 적용
			.apply(new JwtSecurityConfig(tokenProvider))
			
			;
		
		return http.build();
	}
	
	//PassEncoder interface구현체 BCrypt라고 명시 
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	
	
	
	
}
