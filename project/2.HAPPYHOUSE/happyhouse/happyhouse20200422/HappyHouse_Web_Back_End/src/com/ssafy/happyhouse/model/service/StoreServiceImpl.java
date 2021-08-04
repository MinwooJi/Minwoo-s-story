package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.StoreDao;
import com.ssafy.happyhouse.model.dao.StoreDaoImpl;
import com.ssafy.happyhouse.model.dto.StoreInfo;

public class StoreServiceImpl implements StoreService {
	private StoreDao dao;
	public StoreServiceImpl() {
		dao = new StoreDaoImpl();
	}
	@Override
	public List<StoreInfo> searchAll(String dong) {
		try {
			return dao.searchAll(dong);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new HappyHouseException("상점 정보 검색 중 오류 발생");
		}
	}
	@Override
	public List<StoreInfo> search(String dong, String codename1, String codename2) {
		try {
			return dao.search(dong, codename1, codename2);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new HappyHouseException("상점 정보 검색 중 오류 발생");
		}
	}

	public static void main(String[] args) {
		StoreService service = new StoreServiceImpl();
		
		try {
			List<StoreInfo> list = service.search("동숭동", "음식", "한식");
			for (StoreInfo storeInfo : list) {
				System.out.println(storeInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
