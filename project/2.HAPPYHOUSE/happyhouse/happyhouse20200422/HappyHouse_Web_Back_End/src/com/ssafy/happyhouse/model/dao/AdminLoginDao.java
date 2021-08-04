package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.AdminDto;

public interface AdminLoginDao {

	public AdminDto login(String adminid, String adminpwd) throws SQLException;
	
}
