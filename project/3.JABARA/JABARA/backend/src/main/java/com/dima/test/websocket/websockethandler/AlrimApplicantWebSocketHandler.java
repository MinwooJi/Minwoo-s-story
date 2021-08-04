package com.dima.test.websocket.websockethandler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dima.test.dto.PostLikeTable;

@WebServlet
public class AlrimApplicantWebSocketHandler extends TextWebSocketHandler{

	// WebSocketSession은 클라이언트와 서버간의 전이중 통신을 담당하는 객체다.
	// jdbc의 Connection과 유사하다.
	// 클라이언트의 최초 웹소켓 요청시 생성된다.
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	// 새로운 알림이 생겼을 때
	
	public void noticeCourseApplicant(String targetId) throws Exception{
		System.out.println("이까진 왔니???");
		Iterator<WebSocketSession> iterator = sessions.iterator();
		while(iterator.hasNext()) {
			WebSocketSession session = iterator.next();
			try {
				// 클라이언트에게 전송할 알림을 TextMessage객체로 만든다.
				TextMessage message = new TextMessage(targetId+"님이 회원님의 게시물에 좋아요를 눌렀습니다."); 
				session.sendMessage(message);
			} catch (IOException e) {
				// 전송 중 에러가 발생하는 해당 클라이언트와 통신을 담당하는 WebSocketSession 객체를 삭제한다.
				iterator.remove();
			}
		}
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {
		
	}
	
	public void afterConnectionEstablished(WebSocketSession session) {
		// 연결요청이 접수되면 해당 클라이언트와 통신을 담당하는 WebSocketSession 객체가 전달된다.
		// WebSocketSession객체를 Set에 담아둔다.
		sessions.add(session);
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		//클라이언트와 연결이 해제되면 통신을 담당하는 객체를 HashSet에서 제거한다.
		sessions.remove(session);
	}
	
	public void handleTransportError(WebSocketSession session, Throwable exception) {
		// 클라이언트와 데이터 송수신중 에러가 발생해도 해당 클라이언트와 통신을 담당하는 객체를 HashSet에서 제거한다.
		sessions.remove(session);
	}
}
