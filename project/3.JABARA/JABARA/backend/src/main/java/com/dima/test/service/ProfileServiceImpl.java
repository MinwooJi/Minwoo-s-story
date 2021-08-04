package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.CommentDao;
import com.dima.test.dao.ProfileDao;
import com.dima.test.dto.MyApply;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileDao dao;

	@Override
	public List<MyApply> selectMyApply(String userId) {
		return dao.selectMyApply(userId);
	}

	@Override
	public boolean deleteMyApply(int myApplyNumber) {
		return dao.deleteMyApply(myApplyNumber)==1;
	}

	@Override
	public List<Resume> selectMyResume(String userId) {
		return dao.selectMyResume(userId);
	}

	@Override
	public List<ResumeDetail> selectResumeDetail(Resume resume) {
		return dao.selectResumeDetail(resume);
	}

	@Override
	public boolean insertResume(Resume resume) {
		return dao.insertResume(resume)==1;
	}

	@Override
	public boolean updateResume(Resume resume) {
		return dao.updateResume(resume)==1;
	}

	@Override
	public boolean updateResumeDetail(ResumeDetail resumeDetail) {
		return dao.updateResumeDetail(resumeDetail)==1;
	}
	
	@Override
	public boolean deleteResume(Resume resume) {
		return dao.deleteResume(resume)==1;
	}

	@Override
	public boolean deleteQuestion(ResumeDetail resumeDetail) {
		return dao.deleteQuestion(resumeDetail)==1;
	}

	@Override
	public boolean insertQuestion(ResumeDetail resumeDetail) {
		return dao.insertQuestion(resumeDetail)==1;
	}

	@Override
	public boolean updatePass(Resume resume) {
		return dao.updatePass(resume)==1;
	}

	@Override
	public int selectResumeNumber(String userId) {
		return dao.selectResumeNumber(userId);
	}

	@Override
	public boolean insertMyApply(MyApply myApply) {
		return dao.insertMyApply(myApply)==1;
	}

	@Override
	public int selectMyApplyNumber(String userId) {
		return dao.selectMyApplyNumber(userId);
	}
	
	@Override
	public int selectMyApplyNumberMax(String userId) {
		return dao.selectMyApplyNumberMax(userId);
	}

	@Override
	public int selectResumeNumberMax(String userId) {
		return dao.selectResumeNumberMax(userId);
	}

	@Override
	public int selectQuestionNumber(ResumeDetail resumeDetail) {
		return dao.selectQuestionNumber(resumeDetail);
	}

	@Override
	public int selectQuestionNumberMax(ResumeDetail resumeDetail) {
		return dao.selectQuestionNumberMax(resumeDetail);
	}

}
