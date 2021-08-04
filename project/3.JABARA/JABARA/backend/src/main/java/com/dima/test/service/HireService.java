package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.Hire;

public interface HireService {
	public List<Hire> selectHire();
	public boolean insertHire(Hire hire);
	public boolean updateHire(Hire hire);
	public boolean deleteHire(int hireNumber);
	public boolean insertHireToMyCal(Hire hire);
	public int selectHireNumber(Hire hire);
	public int selectHireNumberCount(Hire hire);
	public boolean insertMyApplyFromHire(Hire hire);
	public int setMyApplyNumber();
	public Hire selectHireForMyApply(int hireNumber);
	public int selectMyCalNumberMax(String userId);
	public int selectMyCalNumberCount(String userId);
	public int selectMaxHireNumber();
	public int selectMyApplyNumber(String userId);
}

