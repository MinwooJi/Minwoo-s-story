package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.SocketChat;

public interface SocketChatDao {
	int insertChat(SocketChat chat);
	int findChatRoom(SocketChat chat);
	SocketChat findChatRoomInform(SocketChat chat);	
	List<SocketChat> selectChat(SocketChat chat);
	List<SocketChat> selectChatList(String userId);
}	
