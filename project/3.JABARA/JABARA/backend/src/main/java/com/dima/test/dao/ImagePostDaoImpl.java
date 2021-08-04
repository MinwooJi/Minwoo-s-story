package com.dima.test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Post;
import com.dima.test.dto.PostImage;
import com.dima.test.dto.UserImage;

@Repository
public class ImagePostDaoImpl implements ImagePostDao {
	private static final String NS = "com.dima.test.dao.ImagePostDao.";
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertPostImage(PostImage postimage) {
	return sqlSession.insert(NS+"insertPostImage", postimage);
	}
	
	@Override
	public String selectPostImage(int postNumber) {
	return sqlSession.selectOne(NS+"selectPostImage", postNumber);
	}
	
	@Override
	public int updatePostImage(PostImage postimage) {
	return sqlSession.update(NS+"updatePostImage", postimage);
	}
	
	@Override
	public int deletePostImage(int postNumber) {
	return sqlSession.delete(NS+"deletePostImage", postNumber);
	}


}
