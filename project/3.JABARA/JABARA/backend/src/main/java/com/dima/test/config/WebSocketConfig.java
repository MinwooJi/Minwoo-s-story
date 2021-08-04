

package com.dima.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

	@Configuration	
	// 클래스 선언 앞에 작성합니다. 이 어노테이션은 해당 클래스가 Bean의 설정을 할 것이라는 것을 나타냅니다.
	@EnableWebSocketMessageBroker//@EnableWebSocketMessageBroker is used to enable our WebSocket server
	public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
											// 웹 소켓 연결을 구성하기 위한 메서드를 구현하고 제공합니다.

		
	    @Override
	    // 클라이언트가 메시지를 구독할 endpoint를 정의합니다.
	    public void configureMessageBroker(MessageBrokerRegistry config) {
	        config.enableSimpleBroker("/jobara/send");
	    }

	    @Override
	    // connection을 맺을때 CORS 허용합니다.
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	        registry.addEndpoint("/").setAllowedOrigins("*").withSockJS();
	    }
	    
//	    @Override
//	    public void registerStompEndpoints(StompEndpointRegistry registry) {
//	        registry.addEndpoint("/ws").withSockJS();
//	    }
//
//	    
//	    // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는 데 사용될 메시지 브로커를 구성하고 있습니다.
//	    @Override
//	    public void configureMessageBroker(MessageBrokerRegistry registry) {
//	        registry.setApplicationDestinationPrefixes("/app");
//	        registry.enableSimpleBroker("/topic");
//	        // "/topic" 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의합니다.
//	        //메시지 브로커는 특정 주제를 구독 한 연결된 모든 클라이언트에게 메시지를 broadcast 합니다.
//	    }
	}

	
