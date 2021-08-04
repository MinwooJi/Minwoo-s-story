package com.dima.test.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.MyApply;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	private static final String NS = "com.dima.test.dao.ProfileDao.";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<MyApply> selectMyApply(String userId) {
		List<MyApply> result = sqlSession.selectList(NS+"selectMyApply",userId);
		return result;
	}

	@Override
	public int deleteMyApply(int myApplyNumber) {
		return sqlSession.delete(NS+"deleteMyApply",myApplyNumber);
	}

	@Override
	public List<Resume> selectMyResume(String userId) {
		
		return sqlSession.selectList(NS+"selectMyResume",userId);
	}

	@Override
	public List<ResumeDetail> selectResumeDetail(Resume resume) {
		return sqlSession.selectList(NS+"selectResumeDetail", resume);
	}

	@Override
	public int insertResume(Resume resume) {
		return sqlSession.insert(NS+"insertResume",resume);
	}

	@Override
	public int updateResume(Resume resume) {
		return sqlSession.update(NS+"updateResume",resume);
	}

	@Override
	public int updateResumeDetail(ResumeDetail resumeDetail) {
		return sqlSession.update(NS+"updateResumeDetail",resumeDetail);
	}
	
	@Override
	public int deleteResume(Resume resume) {
		return sqlSession.delete(NS+"deleteResume",resume);
	}

	@Override
	public int deleteQuestion(ResumeDetail resumeDetail) {
		return sqlSession.delete(NS+"deleteQuestion",resumeDetail);
	}

	@Override
	public int insertQuestion(ResumeDetail resumeDetail) {
		return sqlSession.insert(NS+"insertQuestion", resumeDetail);
	}

	@Override
	public int updatePass(Resume resume) {
		return sqlSession.update(NS+"updatePass",resume);
	}

	@Override
	public int selectResumeNumber(String userId) {
		return sqlSession.selectOne(NS+"selectResumeNumber",userId);
	}

	@Override
	public int selectResumeNumberMax(String userId) {
		return sqlSession.selectOne(NS+"selectResumeNumberMax",userId);
	}
	
	@Override
	public int insertMyApply(MyApply myApply) {
		return sqlSession.insert(NS+"insertMyApply",myApply);
	}

	@Override
	public int selectMyApplyNumber(String userId) {
		return sqlSession.selectOne(NS+"selectMyApplyNumber",userId);
	}

	@Override
	public int selectMyApplyNumberMax(String userId) {
		return sqlSession.selectOne(NS+"selectMyApplyNumberMax",userId);
	}
	@Override
	public int selectQuestionNumber(ResumeDetail resumeDetail) {
		return sqlSession.selectOne(NS+"selectQuestionNumber",resumeDetail);
	}

	@Override
	public int selectQuestionNumberMax(ResumeDetail resumeDetail) {
		return sqlSession.selectOne(NS+"selectQuestionNumberMax",resumeDetail);
	}

}
