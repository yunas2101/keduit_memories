package com.kedu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").
		allowedOriginPatterns("*").
		allowedMethods("*").
		allowedHeaders("*").
		allowCredentials(true); // <- 서버와 URL이 다른 곳에서 Credential 정보(Session key) 값을 전송해도 허용하는 요청
	}
}
