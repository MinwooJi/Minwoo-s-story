package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.AdminDto;
import com.ssafy.happyhouse.util.DBUtil;

public class AdminLoginDaoImpl implements AdminLoginDao {

	@Override
	public AdminDto login(String adminid, String adminpwd) throws SQLException {
		AdminDto adminDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select adminname, adminid, email \n");
			sql.append("from admin_menber \n");
			sql.append("where adminid = ? and adminpwd = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, adminid);
			pstmt.setString(2, adminpwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				adminDto = new AdminDto();
				adminDto.setUserid(rs.getString("adminid"));
				adminDto.setUsername(rs.getString("adminname"));
				adminDto.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			adminDto = null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return adminDto;
	}

}
