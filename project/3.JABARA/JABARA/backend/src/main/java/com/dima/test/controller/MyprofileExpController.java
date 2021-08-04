package com.dima.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.Exp;
import com.dima.test.dto.License;
import com.dima.test.dto.Post;
import com.dima.test.dto.ResumeDetail;
import com.dima.test.service.ProfileExpService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/profile")
public class MyprofileExpController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	ProfileExpService profileexpservice; 
	@PostMapping("/insertExp")
	public ResponseEntity<String> insertExp(@RequestBody Exp exp) throws Exception {
		logger.debug("insertExp - 호출");
		String userId = exp.getUserId();
		int expcount= profileexpservice.selectExpCount(userId);
		// 글이 없을 시에 max 함수 사용시 null 에러
		// count로 먼저 계산
		if(expcount==0) {
			expcount=1;
		}else
		{
			//max넘버로 지정
			expcount = profileexpservice.selectExpMax(userId)+1;
		}
		exp.setExpNumber(expcount);		
		if (profileexpservice.insertExp(exp)) {
			return new ResponseEntity<String>(expcount+"", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/insertLicense")
	public ResponseEntity<String> insertLicense(@RequestBody License license) throws Exception {
		logger.debug("insertExpList - 호출");
		if (profileexpservice.insertLicense(license)) {
			String num = license.getLicenseNumber();
			return new ResponseEntity<String>(num, HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	// 자소서 항목 별 검색
	@PostMapping("/selectExpByQ/{keyword}")
	public ResponseEntity<List<ResumeDetail>> selectExpByQ(@PathVariable String[] keyword) throws Exception {
		logger.debug("selectExpByQ - 호출");
		for(String key: keyword) {
			System.out.println(key);
		}
		List<ResumeDetail> list = profileexpservice.selectExpByQ(keyword);
		return new ResponseEntity<List<ResumeDetail>>(list,  HttpStatus.OK);
	}
	
	@PostMapping("/selectExpList")
	public ResponseEntity<List<Exp>> selectExpList(@RequestBody String userId) throws Exception {
		logger.debug("selectExpList - 호출");
		List<Exp> list = profileexpservice.selectExpList(userId);
		return new ResponseEntity<List<Exp>>(list,  HttpStatus.OK);
	}
	@PostMapping("/selectLicenseList")
	public ResponseEntity<List<License>> selectLicenseList(@RequestBody String userId) throws Exception {
		logger.debug("selectLicenseList - 호출");
		List<License> list = profileexpservice.selectLicenseList(userId);
		return new ResponseEntity<List<License>>(list,  HttpStatus.OK);
	}
	
	@PutMapping("/modifyExpList")
	public ResponseEntity<String> modifyExp(@RequestBody Exp exp) throws Exception {
		logger.debug("modifyExp - 호출");
		profileexpservice.modifyExp(exp);
		return new ResponseEntity<String>("success",  HttpStatus.OK);
	}
	@PutMapping("/modifyLicenseList")
	public ResponseEntity<String> modifyLicense(@RequestBody License License) throws Exception {
		logger.debug("modifyLicense - 호출");
		profileexpservice.modifyLicense(License);
		return new ResponseEntity<String>("success",  HttpStatus.OK);
	}
	@DeleteMapping("/deleteExp")
	public ResponseEntity<String>deleteExp(@RequestBody Exp exp) throws Exception{
		logger.debug("deleteExp - 호출");
		profileexpservice.deleteExp(exp);
		return  new ResponseEntity<String>("success",  HttpStatus.OK);
	}
	@DeleteMapping("/deleteLicense")
	public ResponseEntity<String>deleteLicense(@RequestBody License license) throws Exception{
		logger.debug("deleteExp - 호출");
		profileexpservice.deleteLicense(license);
		return  new ResponseEntity<String>("success",  HttpStatus.OK);
	}
	
	
}
