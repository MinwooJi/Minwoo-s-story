package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.MyApply;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

public interface ProfileService {
	public List<MyApply> selectMyApply(String userId);
	public boolean deleteMyApply(int myApplyNumber);
	public List<Resume> selectMyResume(String userId);
	public List<ResumeDetail> selectResumeDetail(Resume resume);
	public boolean insertResume(Resume resume);
	public boolean updateResume(Resume resume);
	public boolean updateResumeDetail(ResumeDetail resumeDetail);
	public boolean deleteResume(Resume resume);
	public boolean insertQuestion(ResumeDetail resumeDetail);
	public boolean deleteQuestion(ResumeDetail resumeDetail);
	public boolean updatePass(Resume resume);
	public int selectResumeNumber(String userId);
	public int selectResumeNumberMax(String userId);
	public boolean insertMyApply(MyApply myApply);
	public int selectMyApplyNumber(String userId);
	public int selectMyApplyNumberMax(String userId);
	public int selectQuestionNumber(ResumeDetail resumeDetail);
	public int selectQuestionNumberMax(ResumeDetail resumeDetail);
}
