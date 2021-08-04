package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.MemberDao;
import com.ssafy.happyhouse.model.dao.MemberDaoImpl;
import com.ssafy.happyhouse.model.dto.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}
	@Override
	public void signUp(Member member) {
		try {
			dao.insertUser(member);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new HappyHouseException("회원가입시 오류 발생");
		}
	}
	@Override
	public List<Member> searchAll() {
		List<Member> list = new LinkedList<Member>();
		try {
			list = dao.searchAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new HappyHouseException("회원가입시 오류 발생");
		}
		return list;
	}
	public static void main(String[] args) {
		List<Member> list = new LinkedList<>();
		Member member = new Member();
		member.setId("jjjj");
		member.setPassword("1234");
		member.setName("김소희");
		member.setAddressDefault("경상북도 구미시 진평동");
		member.setPhonenum("010-1234-1234");
		member.setAddressDetail("로렉스 200호");
		member.setPostnum("22345");
		member.setHomenum("054-222-1234");
		System.out.println("--------id = jjjj, name = 김소희 넣기전--------");
		MemberServiceImpl memImpl = new MemberServiceImpl();
		list = memImpl.searchAll();
		for (Member member2 : list) {
			System.out.println(member2);
		}
		System.out.println("--------id = jjjj, name = 김소희 넣은 후--------");
		memImpl.signUp(member);
		list.clear();
		list = memImpl.searchAll();
		for (Member member2 : list) {
			System.out.println(member2);
		}
	}
	

}
