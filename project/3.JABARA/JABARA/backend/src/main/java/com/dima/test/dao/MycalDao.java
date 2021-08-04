package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.Hire;
import com.dima.test.dto.MyApply;
import com.dima.test.dto.Mycal;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

public interface MycalDao {
	List<Mycal> selectMycal(String userId);
	int insertMycal(Mycal mycal);
	int updateMycal(Mycal mycal);
	int deleteMycal(Mycal mycal);
	int selectHireNumber(Hire hire);
	int selectMyCalNumberMax(String userId);
}
