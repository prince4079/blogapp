package com.sparx.blogapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sparx.blogapplication.repository.UserRepository;

@SpringBootTest
class BlogapplicationApplicationTests {

	@Test
	void contextLoads() {
	}
   @Autowired
	private UserRepository userRepository; 
   
    public void testRepo() {
    	System.out.println("______________________________________");
    	System.out.println(userRepository.getClass().getName());
    	System.out.println(userRepository.getClass().getPackageName());
    	System.out.println("______________________________________");
    }
}
