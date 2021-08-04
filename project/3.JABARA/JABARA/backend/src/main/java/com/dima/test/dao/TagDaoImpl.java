package com.dima.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;
import com.dima.test.dto.Tag;


@Repository
public class TagDaoImpl implements TagDao {
	private static final String NS = "com.dima.test.dao.TagDao.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertTag(String tag) {
		return sqlSession.insert(NS+"insertTag",tag);
	}

	@Override
	public int findTagNumber(String tag) {
		return sqlSession.selectOne(NS+"findTagNumber",tag);
	}

	@Override
	public int insertPostTag(Tag new_tag) {
		return sqlSession.insert(NS+"insertPostTag",new_tag);
	}

	@Override
	public List<String> findTitleTag(int postNumber) {
		return sqlSession.selectList(NS+"findTitleTag",postNumber);
	}

	@Override
	public List<Post> findPostByTag(String tagName) {
		return sqlSession.selectList(NS+"findPostByTag",tagName);
	}
	
	@Override
	public void checkAlgoCategory(Tag tag) {
		System.out.println("checkAlgoCategory");
		sqlSession.insert(NS+"checkAlgoCategory",tag);
		
	}

	@Override
	public void modifyPostTag(AlgoCategoryCount algocount) {
		System.out.println("modifyPostTag");
		sqlSession.update(NS+"modifyPostTag", algocount);
		
	}

	@Override
	public void insertUserTage(String userId) {
		sqlSession.insert(NS+"insertUserTage", userId);
	}

	@Override
	public int selectSolve(int postNumber) {
		return sqlSession.selectOne(NS+"selectSolve", postNumber);
	}

	@Override
	public List<Tag> findTagByPostNumber(int postNumber) {
		return sqlSession.selectList(NS+"findTagByPostNumber",postNumber);
	}
}
