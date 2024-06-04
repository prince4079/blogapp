package com.sparx.blogapplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.blogapplication.entities.CacheUser;
import com.sparx.blogapplication.service.CacheUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/cache")
//@Validated
public class UserCacheController {
	
	CacheUserService userService;
	
	@Autowired
    public UserCacheController(CacheUserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CacheUser> findUserById(@PathVariable("id") String userId){
    	System.out.println("the findByUserId method is getting executed"+userId);
    	CacheUser cacheUser=userService.getUserById(userId);
        return new ResponseEntity<>(cacheUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CacheUser>> findAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CacheUser user){
    	System.out.println("the create method is execuing");
        userService.addUser(user);
        return  new ResponseEntity<>("user created with the name"+user.getUserName(),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CacheUser> updateUser(@PathVariable("id") String userId, @RequestBody  CacheUser user){
        userService.updateUser(userId,user);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CacheUser> deleteUser(@PathVariable("id") @NotNull String userId){
        userService.deleteUser(userId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/allCacheData")
    public Map<String, Object> getAllCacheData() {
        return userService.getAllCacheData();
    }
}
