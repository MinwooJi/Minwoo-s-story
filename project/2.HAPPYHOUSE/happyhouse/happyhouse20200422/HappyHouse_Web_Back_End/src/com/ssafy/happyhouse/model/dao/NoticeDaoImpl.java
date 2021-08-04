package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.DBUtil;


public class NoticeDaoImpl implements NoticeDao {

	@Override
	public void writeNotice(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("insert into notice (adminid, subject, content, regtime) \n");
			insertMember.append("values (?, ?, ?, now())");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, noticeDto.getUserid());
			pstmt.setString(2, noticeDto.getSubject());
			pstmt.setString(3, noticeDto.getContent());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public List<NoticeDto> listNotice(String key, String word) throws SQLException {
		List<NoticeDto> list = new ArrayList<NoticeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select noticeno, adminid, subject, content, regtime \n");
			sql.append("from notice \n");
			if(!word.isEmpty()) {
				if("subject".equals(key)) {
					sql.append("where subject like ? \n");
				} else {
					sql.append("where " + key + " = ? \n");
				}
			}
			sql.append("order by noticeno desc \n");
			pstmt = conn.prepareStatement(sql.toString());
			if(!word.isEmpty()) {
				if("subject".equals(key))
					pstmt.setString(1, "%" + word + "%");
				else
					pstmt.setString(1, word);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setNoticeno(rs.getInt("noticeno"));
				noticeDto.setUserid(rs.getString("adminid"));
				noticeDto.setSubject(rs.getString("subject"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegtime(rs.getString("regtime"));
				
				list.add(noticeDto);
				System.out.println(noticeDto.toString());
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}

	@Override
	public NoticeDto getNotice(int noticeno) throws SQLException {
		NoticeDto noticeDto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select noticeno, adminid, subject, content, regtime \n");
			sql.append("from notice \n");
			sql.append("where noticeno = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				noticeDto = new NoticeDto();
				noticeDto.setNoticeno(rs.getInt("noticeno"));
				noticeDto.setUserid(rs.getString("adminid"));
				noticeDto.setSubject(rs.getString("subject"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegtime(rs.getString("regtime"));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return noticeDto;
	}

	@Override
	public void modifyNotice(NoticeDto noticeDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("update notice \n");
			insertMember.append("set subject = ?, content = ? \n");
			insertMember.append("where noticeno = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setString(1, noticeDto.getSubject());
			pstmt.setString(2, noticeDto.getContent());
			pstmt.setInt(3, noticeDto.getNoticeno());
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public void deleteNotice(int noticeno) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder insertMember = new StringBuilder();
			insertMember.append("delete from notice \n");
			insertMember.append("where noticeno = ?");
			pstmt = conn.prepareStatement(insertMember.toString());
			pstmt.setInt(1, noticeno);
			pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public List<NoticeDto> selectAll() throws SQLException {
		List<NoticeDto> list = new ArrayList<NoticeDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select noticeno, adminid, subject, content, regtime \n");
			sql.append("from notice \n");
//			sql.append("order by noticeno desc \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setNoticeno(rs.getInt("noticeno"));
				noticeDto.setUserid(rs.getString("adminid"));
				noticeDto.setSubject(rs.getString("subject"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegtime(rs.getString("regtime"));
				
				list.add(noticeDto);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}

	@Override
	public int getTotalCount(String key, String word) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(noticeno) \n");
			sql.append("from notice \n");
			if(!word.isEmpty()) {
				if("subject".equals(key)) {
					sql.append("where subject like ? \n");
				}else if("adminid".equals(key)) {
					sql.append("where " + key + " like ? \n");
				}else if("noticeno".equals(key)) {
					sql.append("where " + key + " like ? \n");
				}
			}
			sql.append("order by noticeno desc \n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
			if(!word.isEmpty()) {
				if("subject".equals(key))
					pstmt.setString(1, "%" + word + "%");
				else if("adminid".equals(key))
					pstmt.setString(1, "%" + word + "%");
				else if("noticeno".equals(key))
					pstmt.setString(1, "%" + word + "%");
			}
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
	public List<NoticeDto> listNotice(int currentPage, int sizePerPage, String key, String word) throws SQLException {
		List<NoticeDto> list = new ArrayList<NoticeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select noticeno, adminid, subject, content, regtime \n");
			sql.append("from notice \n");
			if(!word.isEmpty()) {
				if("subject".equals(key)) {
					sql.append("where subject like ? \n");
				}else if("adminid".equals(key)) {
					sql.append("where " + key + " like ? \n");
				}else if("noticeno".equals(key)) {
					sql.append("where " + key + " like ? \n");
				}
			}
			sql.append("order by noticeno desc \n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 0;
			if(!word.isEmpty()) {
				if("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else if("adminid".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else if("noticeno".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
			}

			pstmt.setInt(++idx, (currentPage - 1) * sizePerPage);
			pstmt.setInt(++idx, sizePerPage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				NoticeDto noticeDto = new NoticeDto();
				noticeDto.setNoticeno(rs.getInt("noticeno"));
				noticeDto.setUserid(rs.getString("adminid"));
				noticeDto.setSubject(rs.getString("subject"));
				noticeDto.setContent(rs.getString("content"));
				noticeDto.setRegtime(rs.getString("regtime"));
				
				list.add(noticeDto);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		return list;
	}

}
