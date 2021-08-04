package com.ssafy.happyhouse.model.service;

import java.util.List;
import com.ssafy.happyhouse.model.dto.Member;
public interface MemberService {
	void signUp(Member member);
	List<Member> searchAll();
	Member search(Member member);
	Member searchById(Member member);
	Member searchPwd(Member member);
	void update(Member member);
	void delete(Member member);
}
