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
public class HireDaoImpl implements HireDao {
	private static final String NS = "com.dima.test.dao.HireDao.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Hire> selectHire() {
		List<Hire> result = sqlSession.selectList(NS+"selectHire");
		return result;
	}

	@Override
	public int insertHire(Hire hire) {
		return sqlSession.insert(NS+"insertHire",hire);
	}
	
	@Override
	public int updateHire(Hire hire) {
		return sqlSession.update(NS+"updateHire",hire);
	}
	@Override
	public int deleteHire(int hireNumber) {
		return sqlSession.insert(NS+"deleteHire",hireNumber);
	}
	@Override
	public int insertHireToMyCal(Hire hire) {
		return sqlSession.insert(NS+"insertHireToMyCal",hire);
	}

	@Override
	public int insertMyApplyFromHire(Hire hire) {
		return sqlSession.insert(NS+"insertMyApplyFromHire",hire);
	}

	@Override
	public int setMyApplyNumber() {
		return sqlSession.selectOne(NS+"setMyApplyNumber");
	}

	@Override
	public Hire selectHireForMyApply(int hireNumber) {
		Hire hire = sqlSession.selectOne(NS+"selectHireForMyApply",hireNumber);
		System.out.println(hire.getCompanyName());
		return hire;
	}

	@Override
	public int selectHireNumberCount(Hire hire) {
		return sqlSession.selectOne(NS+"selectHireNumberCount",hire);
	}

	@Override
	public int selectHireNumber(Hire hire) {
		return sqlSession.selectOne(NS+"selectHireNumber",hire);
	}

	@Override
	public int selectMyCalNumberMax(String userId) {
		return sqlSession.selectOne(NS+"selectMyCalNumberMax",userId);
	}

	@Override
	public int selectMaxHireNumber() {
		return sqlSession.selectOne(NS+"selectMaxHireNumber");
	}

	@Override
	public int selectMyCalNumberCount(String userId) {
		return sqlSession.selectOne(NS+"selectMyCalNumberCount",userId);
	}

	@Override
	public int selectMyApplyNumber(String userId) {
		return sqlSession.selectOne(NS+"selectMyApplyNumber",userId);
	}

}
