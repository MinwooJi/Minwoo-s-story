package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.Chat;

public interface ChatDao {
	int insertChat(Chat chat);
	List<Chat> selectChat(String userId);
}
