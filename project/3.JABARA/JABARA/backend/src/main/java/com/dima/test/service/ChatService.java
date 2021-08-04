package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.Chat;

public interface ChatService {
	public boolean insertChat(Chat chat);
	public List<Chat> selectChat(String userId);
}
