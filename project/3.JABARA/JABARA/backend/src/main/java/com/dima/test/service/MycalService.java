package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.Comment;
import com.dima.test.dto.Hire;
import com.dima.test.dto.MyApply;
import com.dima.test.dto.Mycal;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

public interface MycalService {
	public List<Mycal> selectMycal(String userId);
	public boolean insertMycal(Mycal mycal);
	public boolean updateMycal(Mycal mycal);
	public boolean deleteMycal(Mycal mycal);
	public int selectHireNumber(Hire hire);
	public int selectMyCalNumberMax(String userId);
}

