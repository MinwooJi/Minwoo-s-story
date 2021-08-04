package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.SocketChat;

public interface SocketChatService {
	public int insertChat(SocketChat chat);
	public int findChatRoom(SocketChat chat);
	public SocketChat findChatRoomInform(SocketChat chat);	
	public List<SocketChat> selectChat(SocketChat chat);
	public List<SocketChat> selectChatList(String userId);
}
