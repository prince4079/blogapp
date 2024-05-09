//package com.sparx.blogapplication.config;
//
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import io.swagger.v3.oas.models.ExternalDocumentation;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//
//
//public class SwaggerConfig {
//	 @Bean
//	  public GroupedOpenApi publicApi() {
//	      return GroupedOpenApi.builder()
//	              .group("springshop-public")
//	              .pathsToMatch("/**").packagesToScan("com.sparx.blogapplication")
//	              .build();
//	  }
//	 @Bean
//	  public OpenAPI springShopOpenAPI() {
//	      return new OpenAPI()
//	              .info(new Info().title("Blog Application ")
//	              .description("This application is used to create blog and sharing with others ")
//	              .version("v0.0.1")
//	              .license(new License().name("Apache 2.0").url("http://blogapp.org")))
//	              .externalDocs(new ExternalDocumentation()
//	              .description("Blog App  Wiki Documentation")
//	              .url("https://bloagApp.wiki.github.org/docs"));
//	  }
//}
