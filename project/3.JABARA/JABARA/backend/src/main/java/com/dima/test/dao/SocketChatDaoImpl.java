package com.dima.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.SocketChat;

@Repository
public class SocketChatDaoImpl implements SocketChatDao {

private static final String NS = "com.dima.test.dao.SocketChatDao.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertChat(SocketChat chat) {
		return sqlSession.insert(NS+"insertChat",chat);
	}

	@Override
	public int findChatRoom(SocketChat chat) {
		return sqlSession.selectOne(NS+"findChatRoom",chat);
	}

	@Override
	public SocketChat findChatRoomInform(SocketChat chat) {
		return sqlSession.selectOne(NS+"findChatRoomInform",chat);
	}

	@Override
	public List<SocketChat> selectChat(SocketChat chat) {
		return sqlSession.selectList(NS+"selectChat",chat);
	}
	
	@Override
	public List<SocketChat> selectChatList(String userId) {
		return sqlSession.selectList(NS+"selectChatList",userId);
	}
	
	

}
