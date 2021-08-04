package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dao.HouseDaoImpl;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.Search;
import com.ssafy.happyhouse.util.PageNavigation;

public class HouseServiceImpl implements HouseService{
	private HouseDao houseDao;

	public HouseServiceImpl() {
		houseDao =new HouseDaoImpl();
	}
	
	@Override
	public List<HouseDeal> list(int currentPage, int sizePerPage, Search param) throws Exception {
		return houseDao.search(currentPage, sizePerPage, param);
	}
	
	@Override
	public HouseDeal detail(String aptName) throws Exception {
		HouseDeal hd = houseDao.selectApt(aptName);
		return hd;
	}
	
	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String sWord, String sType) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		System.out.println("Service, " + sType + " " + sWord);
		int totalCount = 0;
		if(sType.equals("all")) {
			totalCount = houseDao.getTotalCount(); // 총 게시글 갯수;
		} else {
			totalCount = houseDao.getSelectCount(sWord, sType);
		}
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1; // 총 페이지 갯수
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize; // startRange true : 이전X false : 이전O
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage; // endRange true : 다음X false : 다음O
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	
}


