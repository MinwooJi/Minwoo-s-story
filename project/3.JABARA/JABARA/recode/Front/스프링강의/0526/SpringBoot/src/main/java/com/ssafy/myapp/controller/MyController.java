package com.ssafy.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.myapp.service.EmpService;

@Controller
public class MyController {

	@Autowired
	EmpService service;
	
	@GetMapping(value="/findName")
	public String findAllEmployees(Model model) throws Exception{
		model.addAttribute("msg",service.findName());		
		return "hello";
	}
	
	@GetMapping("/hello")	
	public String hello(Model model) {
	   model.addAttribute("msg", "<font color=red>SSAFY님 부자되세요~!!</font>");
	   return "hello";	
	}
	

}
