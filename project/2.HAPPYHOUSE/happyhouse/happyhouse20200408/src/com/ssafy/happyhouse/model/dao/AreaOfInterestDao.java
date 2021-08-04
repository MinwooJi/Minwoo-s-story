package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.dto.Region;

public interface AreaOfInterestDao {
	void insertAOI(Member member,Region region) throws SQLException;
	List<Region> searchAOI(Member member) throws  SQLException;
	void deleteAOI(Member member, Region region) throws  SQLException;
}
