package com.sparx.blogapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoCcntroller {
    @GetMapping("/home")
	public String getHome() {
		return "index";
	}
}
