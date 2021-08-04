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

import javax.mail.internet.InternetAddress;

import com.dima.test.dto.Follow;
import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;
import com.dima.test.service.FollowService;
import com.dima.test.service.ImageService;
import com.dima.test.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/follow")
public class FollowController {
	@Autowired
	private FollowService followService;
	
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);

	// 팔로우하기
	@ApiOperation(value = "팔로우 기능", response = String.class)
	@PostMapping("/insertFollow")
	public ResponseEntity<String> insertFollow(@RequestBody Follow follow) {
		logger.debug("insertFollow - 호출");
		if (followService.insertFollow(follow)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
		// 팔로우 취소하기
		@ApiOperation(value = "팔로우 취소 기능", response = String.class)
		@PostMapping("/deleteFollow")
		public ResponseEntity<String> deleteFollow(@RequestBody Follow follow) {
			logger.debug("deleteFollow - 호출");
			if (followService.deleteFollow(follow)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}

	// Follow List by Id
	@ApiOperation(value = "자신의 팔로워 목록을 반환한다.", response = String.class)
	@GetMapping("/selectFollowById/{userId}")
	public ResponseEntity<List<Follow>> selectFollowById(@PathVariable String userId) throws Exception {
		logger.debug("followListById - 호출");
		return new ResponseEntity<List<Follow>>(followService.selectFollowById(userId), HttpStatus.OK);
	}
	
	// Follwing List by Id
		@ApiOperation(value = "자신의 팔로잉 목록을 반환한다.", response = String.class)
		@GetMapping("/followingListById/{followingId}")
		public ResponseEntity<List<Follow>> selectFollowingById(@PathVariable String followingId) throws Exception {
			logger.debug("selectFollowingById - 호출");
			return new ResponseEntity<List<Follow>>(followService.selectFollowingById(followingId), HttpStatus.OK);
		}
		
		// Follow Count by Id
		@ApiOperation(value = "자신의 팔로워 숫자를 반환한다.", response = String.class)
		@GetMapping("/selectFollowCountById/{userId}")
		public ResponseEntity<Integer> selectFollowCountById(@PathVariable String userId) throws Exception {
			logger.debug("selectFollowCountById - 호출");
			return new ResponseEntity<Integer>(followService.selectFollowCountById(userId), HttpStatus.OK);
		}
		
		// Follwing Count by Id
		@ApiOperation(value = "자신의 팔로잉 숫자를 반환한다.", response = String.class)
		@GetMapping("/selectFollowingCountById/{userId}")
		public ResponseEntity<Integer> selectFollowingCountById(@PathVariable String userId) throws Exception {
			logger.debug("followingCountById - 호출");
			return new ResponseEntity<Integer>(followService.selectFollowingCountById(userId), HttpStatus.OK);
		}
		
		// 상대방을 팔로우 했냐~안했냐~ 했으면 1 안했으면 0 반환
		@ApiOperation(value = "상대방을 팔로우 했는지 안했는지 반환", response = String.class)
		@PostMapping("/IsFollow")
		public ResponseEntity<Integer> IsFollow(@RequestBody Follow follow) throws Exception {
			logger.debug("IsFollow - 호출");
			return new ResponseEntity<Integer>(followService.IsFollow(follow), HttpStatus.OK);
		}
		
}
