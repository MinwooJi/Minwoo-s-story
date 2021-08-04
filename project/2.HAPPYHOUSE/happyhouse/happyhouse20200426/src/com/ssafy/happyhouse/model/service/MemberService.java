package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.Member;

public interface MemberService {
	boolean signUp(Member m) throws SQLException;
	Member signIn(Member m) throws SQLException;
	void updateMember(Member member) throws SQLException;
	void deleteMember(String id) throws SQLException;
	public Member searchPwd(Member member) throws SQLException;
	public Member searchById(Member member) throws SQLException;
}
