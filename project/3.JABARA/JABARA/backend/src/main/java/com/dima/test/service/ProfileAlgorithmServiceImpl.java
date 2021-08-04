package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.ProfileAlgorithmDao;
import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;

@Service
public class ProfileAlgorithmServiceImpl implements ProfileAlgorithmService {
	@Autowired
	ProfileAlgorithmDao dao;
	@Override
	public AlgoCategoryCount selectList(String userId) {
		return dao.selectList(userId);
	}

}
