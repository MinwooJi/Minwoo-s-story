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
import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.util.DBUtil;

public class HouseDAOImpl implements HouseDAO{
	private Map<String, HouseInfo> houseInfo;
	private Map<String, List<HouseDeal>> deals;
	private int size;
	private List<HouseDeal>search;
	private String[] searchType= {HouseDeal.APT_DEAL, HouseDeal.APT_RENT, HouseDeal.HOUSE_DEAL, HouseDeal.HOUSE_RENT};
	public HouseDAOImpl() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("select count(*) as size from housedeal");
			stmt = con.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			rs = stmt.executeQuery();
			if(rs.next()) {
				this.size = rs.getInt("size");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
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
			sql.append("select * from housedeal where 1=1");
			String dong = bean.getDong();
			String aptName = bean.getAptname();
			if(dong!=null) {
				sql.append(" and dong like ?  ");
			}else if(aptName != null) {
				sql.append(" and AptName like ?  ");
			}
			sql.append(" limit ?, ?");
			
			stmt = con.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			int pageNo = bean.getPageNo();
			if(dong!=null) {
				stmt.setString(1, "%"+dong+"%");
				stmt.setInt(2, (pageNo - 1) * 10);
				stmt.setInt(3, 10);
			}else if(aptName != null) {
				stmt.setString(1, "%"+aptName+"%");
				stmt.setInt(2, (pageNo - 1) * 10);
				stmt.setInt(3, 10);
			}else
			{
				stmt.setInt(1, (pageNo - 1) * 10);
				stmt.setInt(2, 10);
			}
			rs = stmt.executeQuery();
			List<HouseDeal> dealList = new LinkedList<>();
			while(rs.next()) {
				HouseDeal  deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("AptName"));
				deal.setDealAmount(rs.getString("dealAmount"));
				dealList.add(deal);
			}
			return dealList;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	public int getSize(HousePageBean  bean) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("select count(*) as size from housedeal where 1=1");
			String dong = bean.getDong();
			String aptName = bean.getAptname();
			if(dong!=null) {
				sql.append(" and dong like ?  ");
			}else if(aptName != null) {
				sql.append(" and AptName like ?  ");
			}
			
			System.out.println(sql.toString());
			stmt = con.prepareStatement(sql.toString());		
			if(dong!=null) {
				stmt.setString(1, "%"+dong+"%");
			}else if(aptName != null) {
				stmt.setString(1, "%"+aptName+"%");
			}
			rs = stmt.executeQuery();
			if(rs.next()) {
				this.size = rs.getInt("size");
			}
			return size;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		} 
		
		// 아파트 정보의 총 갯수 리턴
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
			sql.append("select * from housedeal where no = ?");
			stmt = con.prepareStatement(sql.toString());
			System.out.println(sql.toString());
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
	@Override
	public List<Notice> searchNotice() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("select * from notice where 1=1 ");
			stmt = con.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			rs = stmt.executeQuery();
			List<Notice> noticeList = new ArrayList<>();
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeno(rs.getInt("noticeno"));
				notice.setAdminid(rs.getString("adminid"));
				notice.setSubject(rs.getString("subject"));
				notice.setContent(rs.getString("content"));
				notice.setRegtime(rs.getDate("regtime"));
				noticeList.add(notice);
			}
			return noticeList;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	@Override
	public Notice searchNoticeByNo(int no) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("select * from notice where noticeno = ?");
			stmt = con.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeno(rs.getInt("noticeno"));
				notice.setAdminid(rs.getString("adminid"));
				notice.setSubject(rs.getString("subject"));
				notice.setContent(rs.getString("content"));
				notice.setRegtime(rs.getDate("regtime"));
				return notice;
			}
			return null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	@Override
	public HouseDeal searchByName(String AptName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder(100);
			sql.append("select * from housedeal where AptName = ?");
			stmt = con.prepareStatement(sql.toString());
			System.out.println(sql.toString());
			stmt.setString(1, AptName);
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
}