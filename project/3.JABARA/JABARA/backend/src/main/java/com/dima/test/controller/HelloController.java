package com.dima.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.User;
import com.dima.test.service.UserService;
@RestController
public class HelloController {
	@Autowired
	private UserService userservice;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello 스프링부트!!";
	}
	
	@RequestMapping(value = "/helloUser/{uid}", method = RequestMethod.GET)
	public ResponseEntity<User> helloJson(@PathVariable String uid) {
		try {
			User user = userservice.userDetailById(uid);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}