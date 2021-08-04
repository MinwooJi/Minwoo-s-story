package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.Search;

public interface HouseDao {
	
	public List<HouseDeal> search(int currentPage, int sizePerPage, Search param) throws Exception;
	
	public HouseDeal selectApt(String aptName) throws SQLException;
	
	public int getTotalCount() throws SQLException;
	
	public int getSelectCount(String sWord, String sType) throws SQLException;
}
