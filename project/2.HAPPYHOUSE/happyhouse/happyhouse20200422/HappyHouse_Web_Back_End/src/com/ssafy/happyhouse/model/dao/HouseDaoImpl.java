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
import com.ssafy.happyhouse.model.dto.Search;
import com.ssafy.happyhouse.util.DBUtil;

public class HouseDaoImpl implements HouseDao{

	@Override
	public List<HouseDeal> search(int currentPage, int sizePerPage, Search param) throws Exception {

		List<HouseDeal> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select "
								+ "	no, "
								+ "	dong, "
								+ "	aptName, "
								+ "	dealAmount, "
								+ " type "
						 + " from housedeal ";
			
			String sType = param.getSearchType();  // 동, 아파트 이름 선택
			if (sType.equals("all") == false) {
				sql += "where " + sType + " like ? ";
			}
			
			sql +=		 " order by no limit ?, ? ";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if (sType.equals("all") == false) {
				pstmt.setString(index++, "%" + param.getSearchWord() + "%");
			}
			System.out.println("DAO, " + sType + " " + param.getSearchWord());
			pstmt.setInt(index++, (currentPage - 1) * sizePerPage);
			pstmt.setInt(index++, sizePerPage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseDeal hd = new HouseDeal();
				
				hd.setNo(Integer.parseInt(rs.getString("no")));
				hd.setDong(rs.getString("dong"));
				hd.setAptName(rs.getString("aptName"));
				hd.setDealAmount(rs.getString("dealAmount"));
				hd.setType(rs.getString("type"));
				
				list.add(hd);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}
	
	public HouseDeal selectApt(String aptName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select "
								+ " AptName, "
								+ " dealAmount, "
								+ " rendMoney, "
								+ " buildYear, "
								+ " area, "
								+ " dealYear, "
								+ " dealMonth, "
								+ " dealDay, "
								+ " dong, "
								+ " jibun "
						 + " from housedeal"
						 + " where AptName = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aptName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				HouseDeal hd = new HouseDeal();
				
				hd.setAptName(rs.getString("AptName"));
				hd.setDealAmount(rs.getString("dealAmount"));
				hd.setRentMoney(rs.getString("rendMoney"));
				hd.setBuildYear(Integer.parseInt(rs.getString("buildYear")));
				hd.setArea(rs.getDouble("area"));
				hd.setDealYear(Integer.parseInt(rs.getString("dealYear")));
				hd.setDealMonth(Integer.parseInt(rs.getString("dealMonth")));
				hd.setDealDay(Integer.parseInt(rs.getString("dealDay")));
				hd.setDong(rs.getString("dong"));
				hd.setJibun(rs.getString("jibun"));
				
				return hd;
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}
	
	@Override
	public int getTotalCount() throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(no) from housedeal order by no";
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return cnt;
	}
	
	@Override
	public int getSelectCount(String sWord, String sType) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("DAO, " + sType + " " + sWord);
		try {
			conn = DBUtil.getConnection();
			String sql = " select count(no) from housedeal "
					+ "where " + sType + " like '%" + sWord + "%'";
			System.out.println("select : " + sql);
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return cnt;
	}
}





