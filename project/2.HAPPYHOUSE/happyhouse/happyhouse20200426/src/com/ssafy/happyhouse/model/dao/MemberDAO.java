package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.Member;

public interface MemberDAO {
	boolean insertMember(Member member) throws SQLException;
	Member login(Member m) throws SQLException;
	void updateMember(Member member) throws SQLException;
	void deleteMember(String id) throws SQLException;
	Member searchPwd(Member member) throws SQLException;
	Member searchById(Member member) throws SQLException;
}
