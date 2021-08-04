package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Member;
public interface MemberService {
	void signUp(Member member);
	List<Member> searchAll();
}
