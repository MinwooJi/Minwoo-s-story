package com.dima.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Follow;

@Repository
public class FollowDaoImpl implements FollowDao {
	private static final String NS = "com.dima.test.dao.FollowDao.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertFollow(Follow follow) {
		return sqlSession.insert(NS+"insertFollow",follow);
	}

	@Override
	public int deleteFollow(Follow follow) {
		return sqlSession.delete(NS+"deleteFollow",follow);
	}

	@Override
	public List<Follow> selectFollowById(String userId) {
		return sqlSession.selectList(NS+"selectFollowById",userId);
	}

	@Override
	public List<Follow> selectFollowingById(String followingId) {
		return sqlSession.selectList(NS+"selectFollowingById",followingId);
	}

	@Override
	public int selectFollowCountById(String userId) {
		return sqlSession.selectOne(NS+"selectFollowCountById",userId);
	}

	@Override
	public int selectFollowingCountById(String userId) {
		return sqlSession.selectOne(NS+"selectFollowingCountById",userId);
	}

	@Override
	public int IsFollow(Follow follow) {
		return sqlSession.selectOne(NS+"IsFollow",follow);
	}
	
}
