package com.dima.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Chat;

@Repository
public class ChatDaoImpl implements ChatDao{
	
	private static final String NS = "com.dima.test.dao.ChatDao.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertChat(Chat chat) {
		return sqlSession.insert(NS+"insertChat",chat);
	}

	@Override
	public List<Chat> selectChat(String userId) {
		List<Chat> result = sqlSession.selectList(NS+"selectChat",userId);
		return result;
	}
}
