package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dima.test.dao.SocketChatDao;
import com.dima.test.dto.SocketChat;

@Service
public class SocketChatServiceImpl implements SocketChatService{

	
	@Autowired
	private SocketChatDao dao;

	@Override
	public int insertChat(SocketChat chat) {
		return dao.insertChat(chat);
	}

	@Override
	public int findChatRoom(SocketChat chat) {
		return dao.findChatRoom(chat);
	}

	@Override
	public SocketChat findChatRoomInform(SocketChat chat) {
		return dao.findChatRoomInform(chat);
	}

	@Override
	public List<SocketChat> selectChat(SocketChat chat) {
		return dao.selectChat(chat);
	}
	
	@Override
	public List<SocketChat> selectChatList(String userId) {
		return dao.selectChatList(userId);
	}

}
