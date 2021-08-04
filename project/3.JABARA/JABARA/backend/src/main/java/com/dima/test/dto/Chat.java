package com.dima.test.dto;

public class Chat {
	private String userId;
	private String chat;
	
	public Chat(String userId, String chat) {
		this.userId = userId;
		this.chat = chat;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	
}
