package com.sparx.blogapplication.service;

import java.util.List;

import com.sparx.blogapplication.payloads.UserDto;
import com.sparx.blogapplication.payloads.UserResponseDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserResponseDto> getAllUsers();
	void deleteUser(Integer userId);

}
