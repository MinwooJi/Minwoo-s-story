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

import com.dima.test.dto.Comment;
import com.dima.test.dto.Post;
import com.dima.test.service.CommentService;
import com.dima.test.service.PostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@ApiOperation(value = "해당 게시글 번호의 댓글 리스트.", response = List.class)
	@GetMapping("/searchComment/{postNumber}")
	public ResponseEntity<List<Comment>> searchComment(@PathVariable int postNumber) throws Exception {
		logger.debug("searchComment - 호출");
		return new ResponseEntity<List<Comment>>(commentService.searchComment(postNumber), HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 입력,  그리고 DB입력 성공여부에 따라 해당게시글의 댓글 cnt를 출력 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insertComment")
	public ResponseEntity<String> insertSns(@RequestBody Comment comment) throws Exception {
		logger.debug("댓글 입력");
		// 이전의 comment 개수에 1 더한 값 가져오기
		System.out.println(comment.getPostNumber());
		int count = commentService.selectCommentCount(comment.getPostNumber());
		System.out.println(count);
		// comment 객체의 CommentNumber에 count값을 넣어준다.
		comment.setCommentNumber(count);
		if (commentService.insertComment(comment)) {
			// post 테이블의 댓글 개수 update
			commentService.increaseComment(comment.getPostNumber());
			String result=count+"";
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	@ApiOperation(value = "댓글 수정,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/updateComment")
	public ResponseEntity<String> updateComment(@RequestBody Comment comment) throws Exception {
		logger.debug("댓글 수정");
		if (commentService.updateComment(comment)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	@ApiOperation(value = "댓글 삭제,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/deleteComment")
	public ResponseEntity<String> deleteComment(@RequestBody Comment comment) throws Exception {
		logger.debug("댓글 삭제");
		if (commentService.deleteComment(comment)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}

	
}
