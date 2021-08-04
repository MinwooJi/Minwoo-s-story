package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.dto.Search;
import com.ssafy.happyhouse.util.PageNavigation;
public interface HouseService {
	
	public List<HouseDeal> list(int currentPage, int sizePerPage, Search param) throws Exception;
	
	public HouseDeal detail(String aptName) throws Exception;
	
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String sWord, String sType) throws Exception;
}
