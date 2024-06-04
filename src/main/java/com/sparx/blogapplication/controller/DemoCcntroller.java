package com.sparx.blogapplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparx.blogapplication.entities.Role;

@RestController
@RequestMapping("/demo")
public class DemoCcntroller {
    @GetMapping("/home")
	public String getHome() {
		return "index";
	}
    @GetMapping("/getAll")
    public List getDemoData(){
    ArrayList list	=new ArrayList();
    list.add(new Role(1,"peter"));
    list.add(new Role(2,"david"));
    list.add(new Role(3,"robin"));
    return list;
    }
}
