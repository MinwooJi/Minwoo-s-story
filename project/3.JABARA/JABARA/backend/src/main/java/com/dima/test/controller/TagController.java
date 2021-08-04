package com.dima.test.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;
import com.dima.test.dto.Tag;
import com.dima.test.dto.TagAndUserId;
import com.dima.test.service.PostService;
import com.dima.test.service.TagService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/tag")
public class TagController {
	@Autowired
	private TagService tagservice;
	
	@Autowired
	private PostService postservice;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// EX) #신혜 #최고 #짱짱맨   같은 문자열 입력이 들어오면 #제외하여 DB에 들어간다. (중복은 무시)
	// TAG table에 들어감 -> tagNumber를 이용하여 POST_TAG table에 들어감
	@ApiOperation(value = "해시태그 입력", response = String.class)
	@PostMapping("/insertTag")
	public ResponseEntity<String> insertTag(@RequestBody TagAndUserId tagAndUserId) throws Exception {
		Tag tag = tagAndUserId.getTag();
		String userId = tagAndUserId.getUserId();
		System.out.println("aa");
		logger.debug("해시태그 입력");
		Pattern MY_PATTERN = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
		Matcher mat = MY_PATTERN.matcher(tag.getTagStr());
		AlgoCategoryCount algocount = new AlgoCategoryCount();;
		while(mat.find()) {
			System.out.println("aa");
			String split_tag = mat.group(1);
			System.out.println(split_tag);
			if(split_tag.equals("해시")) {
				System.out.println("해시예요");
				tag.setCategory("HASH");
				algocount.setHash(1);
				tagservice.checkAlgoCategory(tag);
				System.out.println("빠져나왔나?");
			}
			if(split_tag.equals("스택큐")) {
				tag.setCategory("STACK_QUEUE");
				algocount.setStackQueue(1);
				tagservice.checkAlgoCategory(tag);
			}
			if(split_tag.equals("HEAP")) {
				tag.setCategory("HEAP");
				tagservice.checkAlgoCategory(tag);
				algocount.setHeap(1);
			}
			if(split_tag.equals("정렬")){
				tag.setCategory("SORT");
				tagservice.checkAlgoCategory(tag);
				algocount.setSort(1);
			}
			if(split_tag.equals("완전탐색")){
				tag.setCategory("BROUTE_FORCE");
				tagservice.checkAlgoCategory(tag);
				algocount.setBrouteForce(1);
			}
			if(split_tag.equals("탐욕법")){
				tag.setCategory("GREEDY");
				tagservice.checkAlgoCategory(tag);
				algocount.setGreedy(1);
			}
			if(split_tag.equals("동적계획법")){
				tag.setCategory("DYNAMIC");
				tagservice.checkAlgoCategory(tag);
				algocount.setDynamic(1);
			}
			if(split_tag.equals("DFSBFS")){
				tag.setCategory("DFS_BFS");
				tagservice.checkAlgoCategory(tag);
				algocount.setDfsBfs(1);
			}
			if(split_tag.equals("이분탐색")) {
				tag.setCategory("BINARY");
				tagservice.checkAlgoCategory(tag);
				algocount.setBinary(1);
			}
			if(split_tag.equals("그래프이론")){
				tag.setCategory("GRAPH");
				tagservice.checkAlgoCategory(tag);
				algocount.setGraph(1);
			}
			if(split_tag.equals("시뮬레이션")){
				tag.setCategory("SIMULATION");
				tagservice.checkAlgoCategory(tag);
				algocount.setSimulation(1);
			}
			if(split_tag.equals("기타문제")){
				tag.setCategory("ETC");
				tagservice.checkAlgoCategory(tag);
				algocount.setEtc(1);
			}
			algocount.setUserId(userId);
			tagservice.insertTag(split_tag);
			int num = tagservice.findTagNumber(split_tag);
			tag.setTagNumber(num);
			tagservice.insertPostTag(tag);
		}
		tagservice.insertUserTage(userId);
		if(tagservice.selectSolve(tagAndUserId.getTag().getPostNumber())==1) {
		tagservice.modifyPostTag(algocount);
		}
		return null;
	}
	
	@ApiOperation(value = "태그로 글 찾기", response = String.class)
	@GetMapping("/findPostByTag/{tagName}")
	public ResponseEntity<List<Post>> findPostByTag(@PathVariable String tagName) throws Exception {
		logger.debug("태그로 글 찾기");
		// TAG 테이블에서 태그와 매칭되는 태그 번호를 찾고
		// POST_TAG에서 찾은 태그 번호와 매칭되는 글 번호를 찾아서
		// POST 테이블에서 글을 반환한다.
		return new ResponseEntity<List<Post>>(tagservice.findPostByTag(tagName), HttpStatus.OK);
	}
	
	@ApiOperation(value = "글 번호로 태그 찾기", response = String.class)
	@GetMapping("/findTagByPostNumber/{postNumber}")
	public ResponseEntity<List<Tag>> findTagByPostNumber(@PathVariable int postNumber) throws Exception {
		logger.debug("글 번호로 태그 찾기");
		return new ResponseEntity<List<Tag>>(tagservice.findTagByPostNumber(postNumber), HttpStatus.OK);
	}
}
