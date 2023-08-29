package com.example.demo.auth;

import java.security.Key;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;





@Component 
public class TokenProvider { //implements InitializingBean
//https://velog.io/@limsubin/Spring-Security-JWT-%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%B4%EB%B3%B4%EC%9E%90
	//https://stir.tistory.com/275
	//https://thalals.tistory.com/436 !! 

	/* - ING
	
	private static final long ACCESS_TOKEN_VALID_PERIOD = 1000L * 60 * 60 * 24 * 1; //1일
	private final Key jwtSecretKey;
	
//${jwt.secret-key} : application.yml 파일 읽어오기. 이 키값을 바탕으로 jwt 토큰 생성한다.
//    public TokenProvider(@Value("${jwt.secret-key}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        jwtSecretKey = Keys.hmacShaKeyFor(keyBytes);
//    }
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
*/
}
