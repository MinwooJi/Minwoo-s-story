package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.AdminDto;

public interface AdminLoginService {

	public AdminDto login(String adminid, String adminpwd) throws Exception;
	
}
