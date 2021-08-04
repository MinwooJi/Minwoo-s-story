package com.dima.test.dao;

import java.util.Dictionary;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Follow;

@Repository
public class DictionaryDaoImpl implements DictionaryDao {
	private static final String NS = "com.dima.test.dao.DictionaryDao.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Dictionary> selectDictionaryAll() {
		return sqlSession.selectList(NS+"selectDictionaryAll");
	}

	@Override
	public List<Dictionary> selectDictionaryByName(String word) {
		return sqlSession.selectList(NS+"selectDictionaryByName",word);
	}

	
	
}
