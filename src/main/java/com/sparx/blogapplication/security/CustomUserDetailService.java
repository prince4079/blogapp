package com.sparx.blogapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sparx.blogapplication.entities.User;
import com.sparx.blogapplication.exception.ResourceNotFoundException;
import com.sparx.blogapplication.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		String errorMessage="user not found with this : "+username+"email address";
		User user=userRepository.findByEmail(username).orElseThrow(()-> new 
		ResourceNotFoundException(errorMessage));
		return user;
	}
	
	
}
