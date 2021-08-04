package com.ssafy.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.myapp.dao.EmpDAO;

@Service
public class EmpServiceImpl  implements EmpService{

	@Autowired
	EmpDAO dao;
	
	@Override
	public List<String> findName() {
		return dao.selectAll();
	}
}


