package com.sparx.blogapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sparx.blogapplication.entities.User;
import com.sparx.blogapplication.exception.ResourceNotFoundException;
import com.sparx.blogapplication.payloads.UserDto;
import com.sparx.blogapplication.payloads.UserResponseDto;
import com.sparx.blogapplication.repository.UserRepository;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private PasswordEncoder passwordEncoder; 
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
    private UserRepository userRepository;
	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		User userResult=this.dtoTOUser(user);
		String password=userResult.getPassword();
		String encodedPassword=passwordEncoder.encode(password);
		userResult.setPassword(encodedPassword);
		User savedUser=userRepository.save(userResult);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()-> 
		new ResourceNotFoundException("user","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
	    User updatedUser=	userRepository.save(user);
	    
		return userToDto(updatedUser);
	}
	
	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User userResult=userRepository.findById(userId).orElseThrow(()-> 
		new ResourceNotFoundException("user","id",userId));
		return userToDto(userResult);
	}
	@Cacheable(value="UserDto" ,key="userId")
	@Override
	public List< UserResponseDto > getAllUsers() {
		// TODO Auto-generated method stub
//		List<User> userList=userRepository.findAll();
//		List<UserDto> userDtoList=new ArrayList<>();
//		for(User user :  userList){
//			userDtoList.add(userToDto(user));
//		
//		}
		List<User> userlist=userRepository.findAll();
		List<UserDto> userDtoList=userlist.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		List<UserResponseDto> userResponseDto=userDtoList.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
		return userResponseDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
	  	User user=userRepository.findById(userId).orElseThrow(()-> 
		new ResourceNotFoundException("user","id",userId));
	  	this.userRepository.delete(user);
	}
	
	public User dtoTOUser(UserDto userDto) {
		User user1=modelMapper.map(userDto, User.class);
//		User user1=new User();
//		user1.setUserId(user.getUserId());
//		user1.setName(user.getName());
//		user1.setEmail(user.getEmail());
//		user1.setPassword(user.getPassword());
//		user1.setAbout(user.getAbout());
		return user1;
	}
	public UserDto userToDto(User user) {
		UserDto user1=modelMapper.map(user, UserDto.class);
//		UserDto user1=new UserDto();
//		user1.setUserId(user.getUserId());
//		user1.setEmail(user.getEmail());
//		user1.setName(user.getName());
//		user1.setPassword(user.getPassword());
//		user1.setAbout(user.getAbout());
		return user1;
	}

}
