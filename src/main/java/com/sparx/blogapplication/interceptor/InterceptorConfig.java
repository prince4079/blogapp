package com.sparx.blogapplication.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
    Logger log =LoggerFactory.getLogger(InterceptorConfig.class);
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("InterceptorConfig::addInterceptors");
		//registry.addInterceptor(new BlogAppHandlerInterceptor()).addPathPatterns("/user/allUser");
		registry.addInterceptor(new BlogAppHandlerInterceptor()).addPathPatterns("/user/allUsers");
	}

}
