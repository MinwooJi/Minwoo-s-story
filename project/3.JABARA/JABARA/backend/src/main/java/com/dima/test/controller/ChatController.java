package com.dima.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.Chat;
import com.dima.test.dto.Comment;
import com.dima.test.dto.PostLikeTable;
import com.dima.test.dto.SocketChat;
import com.dima.test.dto.alrimList;
import com.dima.test.service.ChatService;
import com.dima.test.service.CommentService;
import com.dima.test.service.PostService;
import com.dima.test.service.SocketChatService;
import com.dima.test.service.alrimService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@Autowired
	private alrimService alrimservice;
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private SocketChatService socketService;
	
    //receive를 메시지를 받을 endpoint로 설정합니다.
    @MessageMapping("/jobara/receive")

    //send로 메시지를 반환합니다.
    @SendTo("/jobara/send")
    
    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
    @PostMapping("/selectChat")
    public ResponseEntity<List<SocketChat>> SocketHandler(@RequestBody SocketChat chat) throws Exception {
    	// 반환
    	return new ResponseEntity<List<SocketChat>>(socketService.selectChat(chat), HttpStatus.OK );
    }
    
    @PostMapping("/selectChatList")
    public ResponseEntity<List<SocketChat>> selectChatList(@RequestBody String userId) throws Exception {
    	// 반환
    	return new ResponseEntity<List<SocketChat>>(socketService.selectChatList(userId), HttpStatus.OK );        
    }
    
    @PostMapping("/insertChat")
    public ResponseEntity<List<SocketChat>> selectChatList(@RequestBody SocketChat chat) throws Exception {
    	String userId = chat.getUserId();
    	String otherId = chat.getOtherId();
    	System.out.println(userId);
    	System.out.println(otherId);
    	int MessageNumber = 0;
    	String chatRoom = "";
    	// 채팅방이 생성되어 있는지 확인
    	// - 채팅방이 없다
    	if(socketService.findChatRoom(chat)==0) {
    		System.out.println("채팅방 없음");
    		chatRoom = userId+"_"+otherId;
    		System.out.println(chatRoom);
    		// 아무것도 없으니 messageNumber 1로 지정
    		MessageNumber = 1;
    		chat.setMessageNumber(MessageNumber);
    		chat.setChatRoom(chatRoom);
    	}else
    	// - 채팅방이 있다.
    	{
    		System.out.println("채팅방이 있다 !");
    		// 채팅방의 정보를 불러옴
    		SocketChat chatInform = socketService.findChatRoomInform(chat);
    		chat.setChatRoom(chatInform.getChatRoom());
    		// MAX 메세지넘버를 불러와서 +1 해주기
    		MessageNumber = chatInform.getMessageNumber()+1;
    		System.out.println(MessageNumber);
    		chatRoom = chatInform.getChatRoom();
    		chat.setMessageNumber(MessageNumber);
    	}
    	System.out.println("추가할거야");
    	socketService.insertChat(chat);
        return new ResponseEntity<List<SocketChat>>(socketService.selectChatList(userId),HttpStatus.OK );
    }
    
    
    /////////////////////////////////////////////////////////////////////////////
    @PostMapping("/selectActivity/{userId}")
    public ResponseEntity<List<alrimList>> SocketHandler(@PathVariable String userId) throws Exception {
    	
    	System.out.println("프론트에서 넘어온 아이디 : "+userId);
    	List<PostLikeTable> likelist = alrimservice.selectList(userId);
    	System.out.println("likelist 끝냄");
    	List<Comment> comlist = commentService.selectList(userId);
    	System.out.println("comlist 끝냄");
    	List<alrimList> resultlist = new ArrayList<alrimList>();
    	System.out.println("resultlist끝냄");
    	
    	System.out.println(likelist.size());
    	for(int i=0;i<likelist.size();i++) {
    		System.out.println("여기는 드렁오나용");
			alrimList temp = new alrimList();
			String message = likelist.get(i).getTargetuserId()+"님이 회원님의 게시물에 좋아요를 눌렀습니다.";
			temp.setContent(message);
			System.out.println(message);
//			Chat chat = new Chat(likelist.get(i).getTargetuserId().toString(), message);
//			chatService.insertChat(chat);
			temp.setPostNumber(likelist.get(i).getPostNumber());
			temp.setUserId(likelist.get(i).getUserId());
			resultlist.add(temp);
		}
    	System.out.println("resultlist의 크기는 : "+resultlist.size());
    	for(int i=0;i<comlist.size();i++) {
			alrimList temp = new alrimList();
			temp.setDate(comlist.get(i).getCommentDate());
			temp.setContent(comlist.get(i).getTargetuserId()+"님이 회원님의 게시물에 댓글을 달았습니다.");
			System.out.println(temp.getContent());
			temp.setPostNumber(comlist.get(i).getPostNumber());
			temp.setUserId(comlist.get(i).getUserId());
			resultlist.add(temp);
		}
    	System.out.println("resultlist의 크기는 : "+resultlist.size());
//    	System.out.println("여기도 실행이 안되니 . . ?");
//    	List<Chat> result = chatService.selectChat(newChat.getUserId());
//    	System.out.println(result.get(0).getUserId());
//    	for(Chat test : result) {
//    		System.out.println(test.getUserId());
//    		System.out.println(test.getChat());
//    	}
//        // vo에서 getter로 userName을 가져옵니다.
//    	String userId = newChat.getUserId();
//    	// vo에서 setter로 content를 가져옵니다.
//    	String chat = newChat.getChat();
//    	Chat add = new Chat(userId,chat);
//    	chatService.insertChat(add);
//
//        result.add(add);
        
        // 반환
        return new ResponseEntity<List<alrimList>>(resultlist, HttpStatus.OK );
    }
}
