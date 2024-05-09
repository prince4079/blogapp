package com.sparx.blogapplication.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sparx.blogapplication.exception.InterceptorGenratedException;
import com.sparx.blogapplication.jwtauth.JwtHelper;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BlogAppHandlerInterceptor implements HandlerInterceptor {
	
	Logger log=LoggerFactory.getLogger(BlogAppHandlerInterceptor.class);
    
	JwtHelper helper=new JwtHelper();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("BlogAppHandlerInterceptor::preHandle");
		String requestHeader=request.getHeader("Authorization");
		String token =requestHeader.substring("Bearer ".length());
		System.out.println("the recieve token is "+token);
		String name=helper.getUsernameFromToken(token);
		System.out.println("the fetched name from the token is "+name);
		if(!StringUtils.isEmpty(requestHeader )&& name.equals("aman@gmail.com")) {
			return true;
		}else {
			throw new InterceptorGenratedException ("the Url access is denied by the interceptor");
			
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("BlogAppHandlerInterceptor::postHandle");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("BlogAppHandlerInterceptor::afterCompletion");
	}

}
/*Interceptor in java : it is basically used to handle the request even before the handler method or class 
 * or the controller for implenting the concept of the inteceptor in the java we have to implement from the 
 * HandlerInterceptor or extends HanlerInterceptor adapter and we have three available method to overide in this 
 * concept that is preHandle which is executed even before the handle postHandle that is used after the controller is getting 
 * execute but before the view or loggic gets execute an the the after completion is used before the reposne is being send to the
 * user and we have to register this class also and for that we have to define a class and implements from  the 
 * webMvcConfigurer and we have to ovveride a method that is add registory and we have to register the class there
 * then the interceptor concepts is being implemented 
 * */
