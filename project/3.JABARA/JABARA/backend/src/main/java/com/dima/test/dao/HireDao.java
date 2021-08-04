package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.Hire;

public interface HireDao {
	List<Hire> selectHire();
	int insertHire(Hire hire);
	int updateHire(Hire hire);
	int deleteHire(int hireNumber);
	int insertHireToMyCal(Hire hire);
	int selectHireNumber(Hire hire);
	int selectHireNumberCount(Hire hire);
	int insertMyApplyFromHire(Hire hire);
	int setMyApplyNumber();
	Hire selectHireForMyApply(int hireNumber);
	int selectMyCalNumberMax(String userId);
	int selectMaxHireNumber();
	int selectMyCalNumberCount(String userId);
	int selectMyApplyNumber(String userId);
}
