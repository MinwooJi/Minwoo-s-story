package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.MemberDAO;
import com.ssafy.happyhouse.model.dao.MemberDAOImpl;
import com.ssafy.happyhouse.model.dto.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDAO dao;

	public MemberServiceImpl() {
		dao = new MemberDAOImpl();
	}

	@Override
	public boolean signUp(Member m) throws SQLException {
		// TODO Auto-generated method stub
		return dao.insertMember(m);
	}

	@Override
	public Member signIn(Member m) throws SQLException {
		// TODO Auto-generated method stub
		return dao.login(m);
	}
	
	@Override
	public void updateMember(Member member) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateMember(member);
		
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		dao.deleteMember(id);
		
	}
	@Override
	public Member searchPwd(Member member) throws SQLException {
		return dao.searchPwd(member);
	}
	@Override
	public Member searchById(Member member) throws SQLException {
		return dao.searchById(member);
	}
}
