package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dao.AdminLoginDao;
import com.ssafy.happyhouse.model.dao.AdminLoginDaoImpl;
import com.ssafy.happyhouse.model.dto.AdminDto;

public class AdminLoginServiceImpl implements AdminLoginService {

	AdminLoginDao loginDao;
	
	public AdminLoginServiceImpl() {
		loginDao = new AdminLoginDaoImpl();
	}
	
	@Override
	public AdminDto login(String adminid, String adminpwd) throws Exception {
		if(adminid == null || adminpwd == null)
			return null;
		return loginDao.login(adminid, adminpwd);
	}

}
