package com.dima.test.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.dima.test.dto.Chat;
import com.dima.test.dto.Comment;
import com.dima.test.dto.Post;
import com.dima.test.dto.PostLikeTable;
import com.dima.test.dto.TagAndUserId;
import com.dima.test.dto.alrimList;
import com.dima.test.service.ChatService;
import com.dima.test.service.CommentService;
import com.dima.test.service.PostService;
import com.dima.test.service.alrimService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping
public class AlrimController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// 채팅관련
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private alrimService alrimservice;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	//내가 타겟아이디의 이 글을 좋아요 했을까요?
	@ApiOperation(value= "좋아요 여부", response = List.class)
	@PostMapping("/IsLike")
	public ResponseEntity<Integer> IsLike(@RequestBody PostLikeTable postlike) throws Exception {
		return new ResponseEntity<Integer>(alrimservice.selectLikePost(postlike), HttpStatus.OK );
	}
	
	//누가 좋아요헀거나 댓글단거 보여주기
	@ApiOperation(value = "취업 일정 넘겨주기", response = List.class)
	@PostMapping("/clickLike")
	public ResponseEntity<Integer> clickLike(@RequestBody PostLikeTable postlike) throws Exception {
		int postNumber= postlike.getPostNumber();
		System.out.println("like");
		System.out.println(postlike.getUserId());
		System.out.println(postlike.getPostNumber());
		System.out.println(postlike.getTargetuserId());
//		String targetUserId= alrimservice.selectTargetUser(postNumber);
//		System.out.println(targetUserId);
//		postlike.setTargetuserId(targetUserId);
		System.out.println("좋아요 개수 : "+alrimservice.selectLikePost(postlike));
		if(alrimservice.selectLikePost(postlike)>0) {
			//누가 어떤 포스트를 좋아요 취소했는지 table에서 삭제해준다.
			alrimservice.deleteLike(postlike);
			System.out.println("abc");
			//좋아요 취소를 누르면 posttalbe에서 like수를 줄여준다.
			postService.unlikePost(postNumber);
			System.out.println("이부분 끝");
		}
		else {
			System.out.println("좋아요가 0일떄");
			//누가 어떤포스트를 좋아요 눌렀는지 insert해주는것(알림리스트를 뽑기위해)
			alrimservice.insertLike(postlike);
			//포스트 테이블에서 포스트 like수를 올리는것
			postService.likePost(postNumber);
			System.out.println("이부분 끝");
		}
		System.out.println(postNumber);
		int result=alrimservice.selectLikePost(postlike);
		System.out.println(result);
		//posttable likecount올리기
		return new ResponseEntity<Integer>(result, HttpStatus.OK );
	}
//	@DeleteMapping("/DownLike")
//	public ResponseEntity<String> deleteAndUpdateLike(@RequestBody PostLikeTable postlike) throws Exception {
//		int postNumber= postlike.getPostNumber();
//		
//		//누가 어떤 포스트를 좋아요 취소했는지 table에서 삭제해준다.
//		alrimservice.deleteLike(postlike);
//		
//		//좋아요 취소를 누르면 posttalbe에서 like수를 줄여준다.
//		postService.unlikePost(postNumber);
//		//posttable likecount올리기
//		return new ResponseEntity<String>("success", HttpStatus.OK );
//	}
	@PostMapping("/alrimList/{userId}")
	public ResponseEntity<List<alrimList>> selectLikeAndComment(@PathVariable String userId) throws Exception {
		// targetUserId 가 userId (내 아이디)
		//USER_ID는 좋아요나 댓글 누른 사람
		System.out.println("여기는 ??");
		List<PostLikeTable>likelist=alrimservice.selectList(userId);
		System.out.println("=========================");
		System.out.println(likelist.size());
		System.out.println("=========================");
		List<Comment> comlist= commentService.selectList(userId);
		for(Comment com : comlist) {
			System.out.println(com);
		}
		List<alrimList> resultlist = new ArrayList<alrimList>();
//		WebSocketSession webSocket = null;
//		Map<String, Object> message = webSocket.getAttributes();
//
//			Set key = message.keySet();
//			Iterator iterator = key.iterator();
//			for(int i=0; i<message.size(); i++) {
//				System.out.println(message.get(iterator.next()));
//			}
		//정렬하기
		for(int i=0;i<likelist.size();i++) {
			alrimList temp = new alrimList();
			temp.setDate(likelist.get(i).getLikeDate());
			String message = likelist.get(i).getTargetuserId()+"님이 회원님의 게시물에 좋아요를 눌렀습니다.";
			temp.setContent(message);
			
//			Chat chat = new Chat(likelist.get(i).getTargetuserId().toString(), message);
//			chatService.insertChat(chat);
			temp.setPostNumber(likelist.get(i).getPostNumber());
			temp.setUserId(likelist.get(i).getUserId());
			resultlist.add(temp);
			
		}
		for(int i=0;i<comlist.size();i++) {
			alrimList temp = new alrimList();
			temp.setDate(comlist.get(i).getCommentDate());
			System.out.println(comlist.get(i).getCommentDate());
			String message = comlist.get(i).getTargetuserId()+"님이 회원님의 게시물에 댓글을 달았습니다.";
//			System.out.println(message);
			temp.setContent(message);
//			Chat chat = new Chat(comlist.get(i).getTargetuserId().toString(), message);
//			chatService.insertChat(chat);
			temp.setPostNumber(comlist.get(i).getPostNumber());
			temp.setUserId(comlist.get(i).getUserId());
			resultlist.add(temp);
		}
		return new ResponseEntity<List<alrimList>>(resultlist, HttpStatus.OK );
	}
	
	
}
