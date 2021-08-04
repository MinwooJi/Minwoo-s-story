package com.dima.test.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Hire;
import com.dima.test.dto.MyApply;
import com.dima.test.dto.Mycal;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

@Repository
public class MycalDaoImpl implements MycalDao {
	private static final String NS = "com.dima.test.dao.MycalDao.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Mycal> selectMycal(String userId) {
		List<Mycal> result = sqlSession.selectList(NS+"selectMycal",userId);
		return result;
	}

	@Override
	public int insertMycal(Mycal mycal) {
		return sqlSession.insert(NS+"insertMycal",mycal);
	}

	@Override
	public int updateMycal(Mycal mycal) {
		return sqlSession.update(NS+"updateMycal",mycal);
	}

	@Override
	public int deleteMycal(Mycal mycal) {
		return sqlSession.delete(NS+"deleteMycal",mycal);
	}

	@Override
	public int selectHireNumber (Hire hire){
		return sqlSession.selectOne(NS+"selectHireNumber",hire);
	}

	@Override
	public int selectMyCalNumberMax(String userId) {
		return sqlSession.selectOne(NS+"selectMyCalNumberMax",userId);
	}	
}
