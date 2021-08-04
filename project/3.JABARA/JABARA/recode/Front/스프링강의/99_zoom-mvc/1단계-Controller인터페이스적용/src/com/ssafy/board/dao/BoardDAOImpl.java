package com.ssafy.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.vo.Board;

import com.ssafy.util.DBUtil;

public class BoardDAOImpl implements BoardDAO {
	@Override
	public List<Board> selectBoard() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT no, writer, title, content, regtime ");
			sql.append("  from zoomboard ");
			sql.append(" order by no desc \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board dto = new Board();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegtime(rs.getDate("regtime"));
				list.add(dto);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public Board selectBoardByNo(int no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT no, writer, title, content, regtime ");
			sql.append("  from zoomboard ");
			sql.append(" where no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Board dto = new Board();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegtime(rs.getDate("regtime"));
				return dto;
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public void insert(Board board) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO zoomboard(writer, title, content) values(? , ?, ?) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public void deleteBoard(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE from zoomboard where no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

//	@Override
//	public void delete(int no) throws Exception {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = DBUtil.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("DELETE from zoomboard where no = ? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, no);
//			pstmt.executeUpdate();
//		} finally {
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//	}
}




















