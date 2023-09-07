package com.example.demo.auth;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import org.springframework.security.core.userdetails.User;

import com.example.demo.entity.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;


/*
 * Token생성, 유효성 검증 담당 
 * */

@Slf4j
@Component 
public class JwtProvider implements InitializingBean{ //
//https://velog.io/@limsubin/Spring-Security-JWT-%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%B4%EB%B3%B4%EC%9E%90
	//https://stir.tistory.com/275
	//https://thalals.tistory.com/436 !! 

	
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;
	
//${jwt.secret-key} : application.yml 파일 읽어오기. 이 키값을 바탕으로 jwt 토큰 생성한다.
    // 의존성 주입
    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds
    ){
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }
    
    //Bean생성 되고 주입 받은 후, secret값 --Base64--DECODING--->key 변수에 할당 
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("::::::::::[            afterPropertiesSET                  ]:::::::::::::");
		
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	/*
     *		Authentication 객체의 권한 정보 이용 --> 토큰 생성
	 * 
	 * */
	public String createToken(Authentication authentication) {
		
		System.out.println("::::::::::::::[             TOKEN 생성             ]:::::::::::::");
		
		//authorities 설정
		String authorities = authentication.getAuthorities().stream()
											.map(GrantedAuthority::getAuthority)
											.collect(Collectors.joining(","));
		
		//Token만료
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.tokenValidityInMilliseconds);
		
		
		return Jwts.builder()
							.setSubject(authentication.getName())
							.claim(AUTHORITIES_KEY, authorities)
							.signWith(key, SignatureAlgorithm.HS512 )
							.setExpiration(validity)
							.compact();
	}

	/*
	 *	Token에 담겨있는 정보  이용해 Authentication 객체 return
	 * 
	 * */
	public Authentication getAuthentication(String token) {
		
		System.out.println("::::::::::::::::::GET AUTHENTICATION::::::::::::::::::::::");
		
		//Token-->이용해서 Calims생성
		Claims claims = Jwts.parserBuilder()
											.setSigningKey(key)
											.build()
											.parseClaimsJws(token)
											.getBody();
		
		//Claim-->Authentication객체 리턴
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
        																								.map(SimpleGrantedAuthority::new)
        																								.collect(Collectors.toList());
        //Claim과 authorities 이용하여 User 객체 생성
        User principal = new User(claims.getSubject(), "", authorities);
        
        System.out.println(principal.toString());
        
        //Authentication 객체 리턴
        return new UsernamePasswordAuthenticationToken(principal, token, authorities); 
        
        
	}

	// Token 유효성 검증 수행
	public boolean validateToken(String token) {
		
		System.out.println("::::::::::TOKEN Vali CHEK::::::::::::");
		
		
		try {
				//Token 파싱 후 발생하는 예외 캐치하여 문제있으면 F 없으면 T 
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			 logger.info("잘못된JWT토큰 서명");
		}catch(ExpiredJwtException e) {
			 logger.info("만료된 JWT 토큰");
		}catch(UnsupportedJwtException e) {
			 logger.info("지원되지 않는 JWT 토큰");
		}catch(IllegalArgumentException e) {
			 logger.info("잘못된 JWT 토큰");
		}
		return false;
	}
	
	
	
}
