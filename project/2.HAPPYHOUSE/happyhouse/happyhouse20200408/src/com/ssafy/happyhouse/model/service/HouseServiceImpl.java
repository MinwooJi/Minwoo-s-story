package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dao.HouseDaoImpl;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;

public class HouseServiceImpl implements HouseService{
	private HouseDao dao;

	public HouseServiceImpl() {
		dao =new HouseDaoImpl();
	}
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean  bean){
		try {
			boolean[] types = bean.getSearchType();
			int cnt = 0;
			for (boolean t : types) {
				if(t) {
					cnt++;
				}
			}
			if(cnt==0) {
				throw new HappyHouseException("주택 타입중 하나 이상을 선택해야 합니다.");
			}
			System.out.println("dd");
			System.out.println(bean);
			return dao.searchAll(bean);
		}catch(SQLException e) {
			throw new HappyHouseException("거래 정보를 조회중 오류가 발생했습니다.");
		}
	}

	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no) {
		try {
			HouseDeal deal = dao.search(no);
			if(deal == null) {
				throw new HappyHouseException(String.format("거래 번호 %d번에 해당하는 거래정보는 존재하지 않습니다.", no));
			}
			return deal;
		} catch (SQLException e) {
			throw new HappyHouseException("거래 정보를 조회중 오류가 발생했습니다.");
		}
	}
	
}


