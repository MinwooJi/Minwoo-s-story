package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.util.DBUtil;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public boolean insertMember(Member m) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("insert into member(id, password, name, address1, phone, zipcode) ");
			sql.append("values (?, ?, ?, ?, ?, 11000)");
			stmt = con.prepareStatement(sql.toString());
			int idx = 1;
			stmt.setString(idx++, m.getId());
			stmt.setString(idx++, m.getPassword());
			stmt.setString(idx++, m.getName());
			stmt.setString(idx++, m.getAddress());
			stmt.setString(idx++, m.getPhone());
			stmt.executeUpdate();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		return true;
	}
	@Override
	public Member searchById(Member member) throws SQLException {
		Member loginMember = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append("from member ");
			sql.append("where id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setId(rs.getString("id"));
				loginMember.setPassword(rs.getString("password"));
				loginMember.setName(rs.getString("name"));
				loginMember.setAddress(rs.getString("address1"));
				loginMember.setPhone(rs.getString("phone"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return loginMember;
	}
	@Override
	public Member login(Member m) throws SQLException {
		// TODO Auto-generated method stub
		Member loginMember= null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from member where id = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPassword());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				loginMember = new Member();
				loginMember.setId(rs.getString("id"));
				loginMember.setPassword(rs.getString("password"));
				loginMember.setName(rs.getString("name"));
				loginMember.setAddress(rs.getString("address1"));
				loginMember.setPhone(rs.getString("phone"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return loginMember;
	}

	@Override
	public void updateMember(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("update member set password = ?, name = ?, phone = ?, address1 = ? ");
			sql.append("where id = ?");
			stmt = con.prepareStatement(sql.toString());
			int idx = 1;
			stmt.setString(idx++, member.getPassword());
			stmt.setString(idx++, member.getName());
			stmt.setString(idx++, member.getPhone());
			stmt.setString(idx++, member.getAddress());
			stmt.setString(idx++, member.getId());
			
			stmt.executeUpdate();
			
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("delete from member ");
			sql.append("where id = ?");
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, id);
			
			stmt.executeUpdate();
			
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		
	}

	@Override
	public Member searchPwd(Member member) throws SQLException {
		Member loginMember = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append("from member ");
			sql.append("where id = ? and name = ? and phone= ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 1;
			pstmt.setString(idx++, member.getId());
			pstmt.setString(idx++, member.getName());
			pstmt.setString(idx++, member.getPhone());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setId(rs.getString("id"));
				loginMember.setName(rs.getString("name"));
				loginMember.setPassword(rs.getString("password"));
				loginMember.setAddress(rs.getString("address1"));
				loginMember.setPhone(rs.getString("phone"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return loginMember;
	}
	
	

}
