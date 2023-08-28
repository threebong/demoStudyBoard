package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedOrigins("htti://localhost:8080")
//			.allowedMethods("GET","POST","PUT","DELETE");
		 registry.addMapping("/**")
         // 메서드 허용
		         .allowedMethods(
		         		HttpMethod.POST.name(), HttpMethod.GET.name(), 
		         		HttpMethod.PUT.name(), HttpMethod.DELETE.name(), 
		         		HttpMethod.OPTIONS.name()
//		        		 "GET","POST","PUT","DELETE"
		             )
		         // 요청 헤더 허용
		         .allowedHeaders("Authorization", "Content-Type")
		         // 응답 헤더 허용
		         .exposedHeaders("Authorization", "Custom-Header")
		         // 오리진 허용 (요청 보내는 곳)
		         .allowedOrigins("http://localhost:8080"); 
	}
}
