package com.sparx.blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.blogapplication.entities.CaptchaUser;
import com.sparx.blogapplication.entities.User;
import com.sparx.blogapplication.repository.CaptchaUserRepository;
import com.sparx.blogapplication.util.CaptchaUtil;

import cn.apiclub.captcha.Captcha;



@Controller
@RequestMapping("/captcha")
public class CapthaController {
    @Autowired
	CaptchaUserRepository  captchaUserRepository;
	
	@GetMapping("/register")
	public String registerUser(Model model) {
		CaptchaUser  user = new CaptchaUser();
		getCaptcha(user);
		model.addAttribute("user", user);
		return "registerUser";
	}
	
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute CaptchaUser user,
			Model model
			) {
		if(user.getCaptcha().equals(user.getHiddenCaptcha())) {
		captchaUserRepository.save(user);
		model.addAttribute("message", "User Registered successfully!");
		return "redirect:allUsers";
		} 
		else {
		model.addAttribute("message", "Invalid Captcha");
		getCaptcha(user);
		model.addAttribute("user", user);
		}
		return "registerUser";
	}

	@GetMapping("/allUsers")
	public String getAllUsers(Model model) {
		List<CaptchaUser> userList= captchaUserRepository.findAll();
		model.addAttribute("userList", userList);
		return "listUsers";
	}
	
	private void getCaptcha(CaptchaUser user) {
		Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
		user.setHiddenCaptcha(captcha.getAnswer());
		user.setCaptcha(""); // value entered by the User
		user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
		
	}
}
