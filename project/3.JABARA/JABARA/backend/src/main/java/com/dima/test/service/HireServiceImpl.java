package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.HireDao;
import com.dima.test.dto.Hire;

@Service
public class HireServiceImpl implements HireService{

	@Autowired
	private HireDao dao;

	@Override
	public List<Hire> selectHire() {
		return dao.selectHire();
	}

	@Override
	public boolean insertHire(Hire hire) {
		return dao.insertHire(hire)==1;
	}

	@Override
	public boolean updateHire(Hire hire) {
		return dao.updateHire(hire)==1;
	}

	@Override
	public boolean deleteHire(int hireNumber) {
		return dao.deleteHire(hireNumber)==1;
	}
	@Override
	public boolean insertHireToMyCal(Hire hire) {
		return dao.insertHireToMyCal(hire)==1;
	}

	@Override
	public int selectHireNumber(Hire hire) {
		return dao.selectHireNumber(hire);
	}

	@Override
	public boolean insertMyApplyFromHire(Hire hire) {
		return dao.insertMyApplyFromHire(hire)==1;
	}

	@Override
	public int setMyApplyNumber() {
		return dao.setMyApplyNumber();
	}

	@Override
	public Hire selectHireForMyApply(int hireNumber) {
		return dao.selectHireForMyApply(hireNumber);
	}

	@Override
	public int selectHireNumberCount(Hire hire) {
		return dao.selectHireNumberCount(hire);
	}

	@Override
	public int selectMyCalNumberMax(String userId) {
		return dao.selectMyCalNumberMax(userId);
	}

	@Override
	public int selectMyCalNumberCount(String userId) {
		return dao.selectMyCalNumberCount(userId);
	}
	
	@Override
	public int selectMaxHireNumber() {
		return dao.selectMaxHireNumber();
	}

	@Override
	public int selectMyApplyNumber(String userId) {
		return dao.selectMyApplyNumber(userId);
	}


}
