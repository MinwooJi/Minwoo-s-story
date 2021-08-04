package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.dto.Region;
import com.ssafy.happyhouse.util.DBUtil;

public class AreaOfInterestDaoImpl implements AreaOfInterestDao {

	@Override
	public void insertAOI(Member member,Region region) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into areaofinterest ( ");
			sql.append("     id,code ");
			sql.append(" ) values ( ");
			sql.append(" ?,? ");
			sql.append(" ) ");
			System.out.println(sql.toString());
			stmt = con.prepareStatement(sql.toString());
			int index = 1;
			stmt.setString(index++, member.getId());
			stmt.setString(index++, region.getCode());
			stmt.executeUpdate();
			
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

	@Override
	public List<Region> searchAOI(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append(" select * from region where 1=1 ");
			sql.append(" and code in (select code from areaofinterest where id = ? )");
			stmt = con.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			int idx = 1;
			stmt.setString(idx++, member.getId());
			rs = stmt.executeQuery();
			List<Region> regionList = new LinkedList<>();
			while(rs.next()) {
				Region region = new Region();
				region.setCode(rs.getString("code"));
				region.setDomegalopolis(rs.getString("Domegalopolis"));
				region.setCitygugun(rs.getString("Citygugun"));
				region.setDong(rs.getString("dong"));
				regionList.add(region);
			}
			return regionList;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

	@Override
	public void deleteAOI(Member member, Region region) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" delete from areaofinterest ");
			sql.append(" where 1=1 ");
			sql.append(" and id = ? ");
			sql.append(" and code = ? ");
			System.out.println(sql.toString());
			stmt = con.prepareStatement(sql.toString());
			int index = 1;
			stmt.setString(index++, member.getId());
			stmt.setString(index++, region.getCode());
			stmt.executeUpdate();
			
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

}
