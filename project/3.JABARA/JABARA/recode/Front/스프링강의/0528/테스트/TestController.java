package com.ssafy.hrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {
	
	@RequestMapping("/a")
	public String a() {
		
		return "a"; 
	}
	
	@RequestMapping("/b")
	public String b(String uname, int age, @ModelAttribute("job") String job, Model m) {
        m.addAttribute("uname", uname);
        m.addAttribute("age", age);
		return "b"; 
	}

/*
	@ExceptionHandler(value = Exception.class)
	public String c(Exception e, Model m) {
        m.addAttribute("exception",e);
		return "error"; 
	}
*/	
}


