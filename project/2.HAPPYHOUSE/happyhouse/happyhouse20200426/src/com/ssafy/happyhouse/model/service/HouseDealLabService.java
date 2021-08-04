package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDealLab;
import com.ssafy.happyhouse.model.dto.HousePageBean;

public interface HouseDealLabService {
	public List<HouseDealLab> process(HousePageBean  bean) throws SQLException;
}
