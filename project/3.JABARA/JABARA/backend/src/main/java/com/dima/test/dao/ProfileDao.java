package com.dima.test.dao;

import java.util.List;


import com.dima.test.dto.MyApply;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

public interface ProfileDao {
	List<MyApply> selectMyApply(String userId);
	int deleteMyApply(int myApplyNumber);
	List<Resume> selectMyResume(String userId);
	List<ResumeDetail> selectResumeDetail(Resume resume);
	int insertResume(Resume resume);
	int updateResume(Resume resume);
	int updateResumeDetail(ResumeDetail resumeDetail);
	int deleteResume(Resume resume);
	int insertQuestion(ResumeDetail resumeDetail);
	int deleteQuestion(ResumeDetail resumeDetail);
	int updatePass(Resume resume);
	int selectResumeNumber(String userId);
	int selectResumeNumberMax(String userId);
	int insertMyApply(MyApply myApply);
	int selectMyApplyNumber(String userId);
	int selectMyApplyNumberMax(String userId);
	int selectQuestionNumber(ResumeDetail resumeDetail);
	int selectQuestionNumberMax(ResumeDetail resumeDetail);
}
