package com.sparx.blogapplication.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.sparx.blogapplication.entities.CacheUser;
import com.sparx.blogapplication.entities.User;
import com.sparx.blogapplication.exception.UserNotFoundException;
import com.sparx.blogapplication.repository.CacheUserRepository;
import com.sparx.blogapplication.repository.UserRepository;

@Service
public class CacheUserService {
	private CacheUserRepository userRepository;
	private final Cache<String, Object> cache;

	private CacheManager cacheManager;
	Logger log = LoggerFactory.getLogger(CacheUserService.class);
	
	
	@Autowired
	public CacheUserService(CacheUserRepository userRepository, CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		this.userRepository = userRepository;
		this.cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
	}
	
	


	@Cacheable(cacheNames = "users")
	public CacheUser getUserById(String userId) {
		try {
			Thread.sleep(4000);
			log.info("getUserById method is getting executed");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		log.info("getUserById : " + userId);
		Optional<CacheUser> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			log.error("user not found : " + userId);
			throw new UserNotFoundException(userId);
		}
		return userOptional.get();
	}

	public List<CacheUser> getAllUsers() {
		log.info("getAllUsers");
		List<CacheUser> users = userRepository.findAll();
		if (users.isEmpty()) {
			log.error("users not found");
			throw new UserNotFoundException();
		}
		return users;
	}

	public void addUser(CacheUser user) {
		log.info("save user");
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);

		userRepository.save(user);
	}

	@CachePut(key = "#userId", cacheNames = "users")
	public void updateUser(String userId, CacheUser user) {
		log.info("update user : " + userId);

		Optional<CacheUser> userOptional = userRepository.findById(userId);
		//userOptional.get().setUserName(user.getUserName());
		if (userOptional.isEmpty()) {
			log.error("user not found : " + userId);
			throw new UserNotFoundException(userId);
		}

		CacheUser u = userOptional.get();

		BeanUtils.copyProperties(user, u);
		u.setUserId(userId);

		userRepository.save(u);
	}

	@CacheEvict(key = "#userId", cacheNames = "users")
	public void deleteUser(String userId) {
		log.info("delete user");

		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}

		userRepository.deleteById(userId);
	}
	
	 
	    public Map<String, Object> getAllCacheData() {
	        Map<String, Object> cacheData = new HashMap<>();
	        for (Map.Entry<String, Object> entry : cache.asMap().entrySet()) {
	            cacheData.put(entry.getKey(), entry.getValue());
	        }
	        return cacheData;
	    }
}
