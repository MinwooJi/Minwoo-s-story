package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.util.DBUtil;
import com.ssafy.happyhouse.util.HouseSaxParser;

public class HouseDaoImpl implements HouseDao{
	private Map<String, HouseInfo> houseInfo;
	private Map<String, List<HouseDeal>> deals;
	private int size;
	private List<HouseDeal>search;
	private String[] searchType= {HouseDeal.APT_DEAL, HouseDeal.APT_RENT, HouseDeal.HOUSE_DEAL, HouseDeal.HOUSE_RENT};
	public HouseDaoImpl() {
	}
	/**
	 * 아파트 정보와 아파트 거래 정보를  xml 파일에서 읽어온다.
	 */
	public void loadData() { }
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean  bean) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append(" select * from housedeal where 1=1 ");
			boolean[] type = bean.getSearchType();
			sql.append(" and type in ( ");
			for (int i =0, size=type.length; i < size; i++) {
				if(type[i]) {
					sql.append(i+1);
					if(i <size-1) {
						sql.append(",");
					}
				}
			}
			if (type[3] == false) { sql.delete(sql.length() -1, sql.length()); }
			sql.append(" ) ");
			String dong = bean.getDong();
			String aptName = bean.getAptname();
			if(dong!=null) {
				sql.append(" and dong like ?  ");
			}else if(aptName != null) {
				sql.append(" and AptName like ?  ");
			}
			
			stmt = con.prepareStatement(sql.toString());
			if(dong!=null) {
				stmt.setString(1, "%"+dong+"%");
			}else if(aptName != null) {
				stmt.setString(1, "%"+aptName+"%");
			}
			rs = stmt.executeQuery();
			List<HouseDeal> dealList = new LinkedList<>();
			while(rs.next()) {
				HouseDeal  deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("AptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				dealList.add(deal);
			}
			
			return dealList;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환한다.<br/>
	 * 법정동+아파트명을 이용하여 HouseInfo에서 건축연도, 법정코드, 지번, 이미지 정보를 찾아서 HouseDeal에 setting한 정보를 반환한다. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append(" select * from housedeal where no =? ");
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				HouseDeal  deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("AptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				return deal;
			}
			return null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	public static void main(String[] args) {
		HouseDao dao = new HouseDaoImpl();
		
		try {
			HouseDeal deal = dao.search(1);
			System.out.println(deal);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		try {
//			HousePageBean bean = new HousePageBean();
//			bean.setSearchType(new boolean[] {true, true, true, true,});
////			bean.setDong("신월");
//			bean.setAptname("롯데");
//			List<HouseDeal> list = dao.searchAll(bean);
//			
//			for (HouseDeal houseDeal : list) {
//				System.out.println(houseDeal);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}





