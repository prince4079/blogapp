package com.sparx.blogapplication.config;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sparx.blogapplication.jwtauth.JWTAuthenticationEntryPoint;
import com.sparx.blogapplication.jwtauth.JwtAuthenticationFilter;





@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {


//    @Autowired
//   
//    private UserDetailsService userDetailService;
	 
	@Autowired
	private UserDetailsService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAll()
                .requestMatchers(
                	    "/swagger-ui/**",
                	    "/captcha/**",
                	    "/auth/login",
                		"swagger-ui/**",
                		"/v3/api-docs/**",
                		"/user/**",
                		"/user/create",
                		"/books/**",
                		"/file/uploadtodb",
                		"/file/fileSystem/**"
                		).permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() { 
	  DaoAuthenticationProvider  daoAuthenticationProvider=new DaoAuthenticationProvider();
	  daoAuthenticationProvider.setUserDetailsService(userDetailService);
	  daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
	  return daoAuthenticationProvider;
  }

  /*
   * 
   *  
   * .requestMatchers("/swagger-ui.html",
                		"/v3/api-docs",
                		"swagger-resources",
                		"swagger-resources/**",
                		"swagger-resources/**",
                		"swagger-ui/**",
                		"/v3/api-docs/**"
                		).permitAll() */
}
