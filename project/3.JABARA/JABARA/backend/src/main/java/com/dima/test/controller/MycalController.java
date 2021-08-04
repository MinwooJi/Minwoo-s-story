package com.dima.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.MyApply;
import com.dima.test.dto.Mycal;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;
import com.dima.test.service.HireService;
import com.dima.test.service.MycalService;
import com.dima.test.service.ProfileService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/Mycal")
public class MycalController {
	@Autowired
	private MycalService mycalService;
	
	@Autowired
	private HireService hireService;
	
	private static final Logger logger = LoggerFactory.getLogger(MycalController.class);
	
	//마이 캘린더 목록 넘겨주기
	@ApiOperation(value = "마이 캘린더 목록 넘겨주기", response = List.class)
	@GetMapping("/selectMycal/{userId}")
	public ResponseEntity<List<Mycal>> selectMycal(@PathVariable String userId) throws Exception {
		logger.debug("selectMycal - 호출");
		return new ResponseEntity<List<Mycal>>(mycalService.selectMycal(userId), HttpStatus.OK);
	}
	
	//마이 캘린더 일정 추가
	@ApiOperation(value = "마이 캘린더 일정 추가", response = List.class)
	@PostMapping("/insertMycal")
	public ResponseEntity<String> insertMycal(@RequestBody Mycal mycal) throws Exception {
		logger.debug("insertMycal - 호출");
		int calNumber = 0;
		String userId = mycal.getUserId();
		if(hireService.selectMyCalNumberCount(userId)==0) {
			calNumber = 1;
		}else {
			calNumber = hireService.selectMyCalNumberMax(userId)+1;
		}
		mycal.setCalNumber(calNumber);
		if (mycalService.insertMycal(mycal)) {
			return new ResponseEntity<String>(calNumber+"", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	//마이 캘린더 일정 수정
	@ApiOperation(value = "마이 캘린더 일정 수정", response = List.class)
	@PostMapping("/updateMycal")
	public ResponseEntity<String> updateMycal(@RequestBody Mycal mycal) throws Exception {
		logger.debug("updateMycal - 호출");
		if (mycalService.updateMycal(mycal)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	//마이 캘린더 일정 삭제
	@ApiOperation(value = "마이 캘린더 일정 삭제", response = List.class)
	@PostMapping("/deleteMycal")
	public ResponseEntity<String> deleteMycal(@RequestBody Mycal myCal) throws Exception {
		logger.debug("deleteMycal - 호출");
		if (mycalService.deleteMycal(myCal)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
}
