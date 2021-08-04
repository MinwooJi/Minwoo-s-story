package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.dto.Region;

public interface AOIService {
	List<Region> searchAll(Member member);
	void insertAOI(Member member,Region region);
	void deleteAOI(Member member,Region region);
}
