package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.dto.StoreInfo;
public interface StoreService {
	/**
	 * 법적동의 모든 상점 정보 검색
	 * @param dong  법적동
	 * @return 조회한 법적동의 상점 정보
	 */
	public List<StoreInfo> searchAll(String dong);
	
	/**
	 * 대분류+중분류로 상업 정보 검색
	 * @param codename1	검색할 대분류명
	 * @param codename2	검색할 중분류명
	 * @return			검색 조건에해당하는 상점 정보를 출력한다.
	 */
	public List<StoreInfo> search(String dong, String codename1, String codename2);
	
}
