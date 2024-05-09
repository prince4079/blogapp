package com.sparx.blogapplication.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.blogapplication.payloads.ApiResponse;
import com.sparx.blogapplication.payloads.UserDto;
import com.sparx.blogapplication.payloads.UserResponseDto;
import com.sparx.blogapplication.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name="User Controller " , description = "This is User Controller and performs task related to the user Entity")
public class UserController {
	@Autowired
	private UserService userService;
 
	// add a User
	@Operation(summary="create new User ", description = "this api will create New User ")
	@ApiResponses(value= {
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",description="User Creatation Sucess | ok "),
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="404",description="not authorsied  !! "),
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="201",description="new User Created  "),
	})
	 @PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
	   UserDto user=userService.createUser(userDto);
	   return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	// get a User
	 @GetMapping("/{userId}")
	 public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
		UserDto user= userService.getUserById(userId);
		return ResponseEntity.ok(user);
	 }
	
	// get All User 
	 
	 @GetMapping("/allUser")
	 public List<UserResponseDto> getAllUsers(){
		 List<UserResponseDto> userlist=userService.getAllUsers();
		 return userlist;
	 }
	
	// update a User 
	 @PutMapping("/{userId}")
	 public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
		 UserDto user=userService.updateUser(userDto, userId);
		 return ResponseEntity.ok(user);
	 }
	 
	// delete a User 
	  @DeleteMapping("/{userId}")
	  @PreAuthorize("hasRole('ADMIN')")
	 public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		 userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("user Deleted Sucessfully",true),HttpStatus.OK);
	 }
	
}
