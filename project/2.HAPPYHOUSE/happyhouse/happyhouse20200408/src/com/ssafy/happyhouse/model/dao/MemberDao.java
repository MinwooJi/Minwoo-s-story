package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Member;

public interface MemberDao {
	// 회원에 대해서 가입(등록),수정,탈퇴(삭제)
	// 로그인은 별도의 dao를 만들어서 할것
	void insertUser(Member member) throws SQLException;
	List<Member> searchAll() throws SQLException;
}
