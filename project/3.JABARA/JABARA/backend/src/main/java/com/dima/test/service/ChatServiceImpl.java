package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.ChatDao;
import com.dima.test.dto.Chat;

@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	private ChatDao dao;
	
	public boolean insertChat(Chat chat) {
		return dao.insertChat(chat)==1;
	}

	@Override
	public List<Chat> selectChat(String userId) {
		return dao.selectChat(userId);
	}
}
