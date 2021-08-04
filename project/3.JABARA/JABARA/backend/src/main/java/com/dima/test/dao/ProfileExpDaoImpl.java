package com.dima.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Exp;
import com.dima.test.dto.License;
import com.dima.test.dto.ResumeDetail;

@Repository
public class ProfileExpDaoImpl implements ProfileExpDao {
	private static final String NS = "com.dima.test.dao.ProfileExpDao.";
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertExp(Exp exp) {
		return sqlSession.insert(NS+"insertExp",exp);
	}

	@Override
	public int insertLicense(License license) {
		return  sqlSession.insert(NS+"insertLicense",license);
	}

	@Override
	public int selectExpCount(String userId) {
		int number = sqlSession.selectOne(NS+"selectExpCount",userId);
		System.out.println(number);
		return number;
	}

	@Override
	public int selectLicenseCount(String userId) {
		return sqlSession.selectOne(NS+"selectLicenseCount",userId);
	}

	@Override
	public List<Exp> selectExpList(String userId) {
		List<Exp> list = sqlSession.selectList(NS+"selectExpList",userId);
		return list;
	}

	@Override
	public List<License> selectLicenseList(String userId) {
		List<License> list =sqlSession.selectList(NS+"selectLicenseList",userId);
		return list;
	}

	@Override
	public void updateExp(Exp exp) {
		sqlSession.update(NS+"updateExp", exp);
	}

	@Override
	public void updateLicense(License license) {
		sqlSession.update(NS+"updateLicense", license);
	}

	@Override
	public void deleteExp(Exp exp) {
		sqlSession.delete(NS+"deleteExp", exp);
	}

	@Override
	public void deleteLicense(License license) {
		sqlSession.delete(NS+"deleteLicense", license);
	}

	// keyword[0] = id 
	// keyword[1] = 통/불통 
	// keyword[2] = 단어 검색
	@Override
	public List<ResumeDetail> selectExpByQ(String[] keyword) {
			String userId = keyword[0];
			String IsPass = keyword[1];
			String key = keyword[2];
		if(IsPass.length()==0) {
			if(key.length()==0) {
				return sqlSession.selectList(NS+"selectExpAll",userId);
			}
			System.out.println("통과/불통과가 없다");
			return sqlSession.selectList(NS+"selectExpByQuestion",keyword);
		}
		else if(key.length()==0) {
			System.out.println("키워드가 없다");
			return sqlSession.selectList(NS+"selectExpBySolved",keyword);
		}else {
			System.out.println("다 있다");
			return sqlSession.selectList(NS+"selectExpBySolvedAndQuestion",keyword);
		}
	}

	@Override
	public int selectExpMax(String userId) {
		return sqlSession.selectOne(NS+"selectExpMax",userId);
	}
}
