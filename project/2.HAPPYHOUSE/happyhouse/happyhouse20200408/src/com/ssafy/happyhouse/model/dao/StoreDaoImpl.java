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
import com.ssafy.happyhouse.model.dto.StoreInfo;
import com.ssafy.happyhouse.util.DBUtil;
import com.ssafy.happyhouse.util.HouseSaxParser;

public class StoreDaoImpl implements StoreDao{
	public StoreDaoImpl() {}

	@Override
	public List<StoreInfo> searchAll(String dong) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from store where dong=? ";			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, dong);
			
			rs = stmt.executeQuery();
			
			List<StoreInfo> list = new ArrayList<StoreInfo>();
			while(rs.next()) {
				list.add(new StoreInfo(
						rs.getInt("no"),
						rs.getString("shopname"),
						rs.getString("localname"),
						rs.getString("code1"),
						rs.getString("codename1"),
						rs.getString("code2"),
						rs.getString("codename2"),
						rs.getString("code3"),
						rs.getString("codename3"),
						rs.getString("code4"),
						rs.getString("codename4"),
						rs.getString("citycode"),
						rs.getString("city"),
						rs.getString("gucode"),
						rs.getString("gu"),
						rs.getString("dongcode"),
						rs.getString("dong"),
						rs.getString("jibunaddress"),
						rs.getString("address"),
						rs.getString("oldpostcode"),
						rs.getString("postcode"),
						rs.getString("lng"),
						rs.getString("lat")
				));
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	@Override
	public List<StoreInfo> search(String dong, String codename1, String codename2) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from store where dong = ? and codename1 = ? and codename2 = ?";
			stmt = con.prepareStatement(sql);
			int index = 1;
			stmt.setString(index++, dong);
			stmt.setString(index++, codename1);
			stmt.setString(index, codename2);
			
			rs = stmt.executeQuery();
			List<StoreInfo> list = new ArrayList<StoreInfo>();
			while(rs.next()) {
				list.add(new StoreInfo(
						rs.getInt("no"),
						rs.getString("shopname"),
						rs.getString("localname"),
						rs.getString("code1"),
						rs.getString("codename1"),
						rs.getString("code2"),
						rs.getString("codename2"),
						rs.getString("code3"),
						rs.getString("codename3"),
						rs.getString("code4"),
						rs.getString("codename4"),
						rs.getString("citycode"),
						rs.getString("city"),
						rs.getString("gucode"),
						rs.getString("gu"),
						rs.getString("dongcode"),
						rs.getString("dong"),
						rs.getString("jibunaddress"),
						rs.getString("address"),
						rs.getString("oldpostcode"),
						rs.getString("postcode"),
						rs.getString("lng"),
						rs.getString("lat")
				));
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
}









