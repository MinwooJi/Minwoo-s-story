package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dao.HouseDAO;
import com.ssafy.happyhouse.model.dao.HouseDAOImpl;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseDealLab;
import com.ssafy.happyhouse.model.dto.HousePageBean;

public class HouseDealLabServiceImpl implements HouseDealLabService {
	HouseDAO houseDAO;
	
	public HouseDealLabServiceImpl() {
		houseDAO = new HouseDAOImpl();
	}

	public List<HouseDealLab> process(HousePageBean bean) throws SQLException {
		List<HouseDealLab> labList = new ArrayList<>();
		for (HouseDeal hd : houseDAO.searchAll(bean)) {
			HouseDealLab hdl = new HouseDealLab();
			hdl.setNo(hd.getNo());
			hdl.setDong(hd.getDong());
			hdl.setAptName(hd.getAptName());
			StringBuilder sb = new StringBuilder();
			String str = hd.getDealAmount().trim();
			for (int i = 0; i < str.length(); ++i) if(str.charAt(i)!=',') sb.append(str.charAt(i));
			hdl.setDealAmountInt(Integer.parseInt(sb.toString()));
			labList.add(hdl);
		}
		labList.sort((x, y) -> ((Integer)x.getDealAmountInt()).compareTo(y.getDealAmountInt()));
		List<HouseDealLab> mMList = new ArrayList<>();
		mMList.add(labList.get(0));
		mMList.add(labList.get(labList.size()-1));
		return mMList;
	}
}
