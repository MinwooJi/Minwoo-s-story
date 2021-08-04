package com.dima.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.MyApply;
import com.dima.test.dto.Post;
import com.dima.test.service.ProfileAlgorithmService;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/profile")
public class ProfileArgorithmController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	ProfileAlgorithmService paservice; 
	
	//그래프 먼저 보여주기 위한 데이터 뽑기
	@PostMapping("/graphalgolist")
	public ResponseEntity<AlgoCategoryCount> graphalgolist(@RequestBody String userId) throws Exception {
		AlgoCategoryCount result= paservice.selectList(userId);
		return new ResponseEntity<AlgoCategoryCount>(result, HttpStatus.OK);
	}
	
	
	
}
