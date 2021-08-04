package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.CommentDao;
import com.dima.test.dao.MycalDao;
import com.dima.test.dao.ProfileDao;
import com.dima.test.dto.Comment;
import com.dima.test.dto.Hire;
import com.dima.test.dto.MyApply;
import com.dima.test.dto.Mycal;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;

@Service
public class MycalServiceImpl implements MycalService{

	@Autowired
	private MycalDao dao;

	@Override
	public List<Mycal> selectMycal(String userId) {
		return dao.selectMycal(userId);
	}

	@Override
	public boolean insertMycal(Mycal mycal) {
		return dao.insertMycal(mycal)==1;
	}

	@Override
	public boolean updateMycal(Mycal mycal) {
		return dao.updateMycal(mycal)==1;
	}

	@Override
	public boolean deleteMycal(Mycal mycal) {
		return dao.deleteMycal(mycal)==1;
	}

	@Override
	public int selectHireNumber(Hire hire) {
		return dao.selectHireNumber(hire);
	}

	@Override
	public int selectMyCalNumberMax(String userId) {
		return dao.selectMyCalNumberMax(userId);
	}


}
