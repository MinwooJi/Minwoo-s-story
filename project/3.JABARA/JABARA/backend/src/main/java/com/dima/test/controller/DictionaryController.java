package com.dima.test.controller;

import java.util.Dictionary;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.service.DictionaryService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService dictionaryService;
	
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);

	// 사전 목록 가져오기
	@ApiOperation(value = "사전 목록 가져오기", response = String.class)
	@PostMapping("/selectDictionaryAll")
	public ResponseEntity<List<Dictionary>> selectDictionaryAll() {
		logger.debug("selectDictionaryAll - 호출");
		return new ResponseEntity<List<Dictionary>>(dictionaryService.selectDictionaryAll(), HttpStatus.OK);
	}
	
	// 사전 단어로 검색하기
	@ApiOperation(value = "사전 단어로 검색하기", response = String.class)
	@PostMapping("/selectDictionaryByName/{word}")
	public ResponseEntity<List<Dictionary>> selectDictionaryByName(@PathVariable String word) {
		logger.debug("insertFollow - 호출");
		return new ResponseEntity<List<Dictionary>>(dictionaryService.selectDictionaryByName(word), HttpStatus.OK);
	}
}
