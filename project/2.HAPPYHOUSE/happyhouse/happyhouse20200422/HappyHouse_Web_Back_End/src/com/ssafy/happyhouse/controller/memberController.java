package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.MemberServiceImpl;
@WebServlet("/member.do")
public class memberController extends HttpServlet {
	MemberService memberService;
	
	public memberController() {
		memberService = new MemberServiceImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request,response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String act = request.getParameter("act");
		System.out.println(act);
		String path = "";
		switch(act == null ? "" : act) {
		case "loginform":
			path = loginForm(request,response);
			break;
		case "logout":
			path = logout(request,response);
			break;
		case "signupform":
			path = signUpForm(request,response);
			break;
		case "login":
			path = login(request,response);
			break;
		case "signup":
			path = signUp(request,response);
			break;
		case "updateform":
			path = updateform(request,response);
			break;
		case "update":
			path = update(request,response);
			break;
		case "delete":
			path = delete(request,response);
			break;
		case "findpwdform":
			path = findpwdform(request,response);
			break;
		case "findpwd":
			path = findpwd(request,response);
			break;
		case "setnewpwdform":
			path = setnewpwdform(request,response);
			break;
		case "setnewpwd":
			path = setnewpwd(request,response);
			break;
		case "selectall":
			path = selectall(request,response);
			break;
		default :
			path = "/main.jsp";
		}
		if(path.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + path.substring("redirect:".length()));
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	private String selectall(HttpServletRequest request, HttpServletResponse response) {
		List<Member> list = memberService.searchAll();
		request.setAttribute("list", list);
		return "/user/selectall.jsp";
	}
	private String setnewpwdform(HttpServletRequest request, HttpServletResponse response) {
		return "/user/setnewpwdform.jsp";
	}
	private String setnewpwd(HttpServletRequest request, HttpServletResponse response) {
		String password1 = request.getParameter("pw1");
		String password2 = request.getParameter("pw2");
		String path = "";
		System.out.println(password1);
		System.out.println(password2);
		if(password1.equals(password2)) {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("memberInfo");
			member = memberService.searchById(member);
			member.setPassword(password1);
			System.out.println(member);
			memberService.update(member);
			session.invalidate();
			return "/user/setnewpwdsuccess.jsp";
		}else {
			request.setAttribute("errMsg", "비밀번호를 확인해 주세요.");
			return "/user/setnewpwdform.jsp";
		}
	}
	private String findpwdform(HttpServletRequest request, HttpServletResponse response) {
		return "/user/findpwdform.jsp";
	}
	private String findpwd(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phoneNum = request.getParameter("phoneNum");
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setPhonenum(phoneNum);
		System.out.println(member);
		Member memberInfo = memberService.searchPwd(member);
		if(memberInfo != null) {
			// 회원정보 있음
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", member);
		}
		else {
			request.setAttribute("errMsg", "존재 하지 않는 회원정보입니다.");
		}
		return "/user/findpwdresult.jsp";
	}
	private String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		memberService.delete(member);
		session.invalidate();
		return "/user/deletesuccess.jsp";
	}
	private String update(HttpServletRequest request, HttpServletResponse response) {
		String password,name,addressDefault,phoneNum,addressDetail,postNum,homeNum;
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember");
		password = request.getParameter("password");
		name = request.getParameter("name");
		addressDefault = request.getParameter("address_default");
		phoneNum = request.getParameter("phoneNum");
		addressDetail = request.getParameter("address_detail");
		postNum = request.getParameter("postNum");
		homeNum = request.getParameter("homeNum");
		
		member.setPassword(password);
		member.setName(name);
		member.setAddressDefault(addressDefault);
		member.setPhonenum(phoneNum);
		member.setAddressDetail(addressDetail);
		member.setPostnum(postNum);
		member.setHomenum(homeNum);
		
		Member overlapCheck = memberService.searchById(member);
		System.out.println(member);
		System.out.println(overlapCheck);
		
		memberService.update(member);
		session.invalidate();
		
		return "/user/updatesuccess.jsp";
	}
	private String updateform(HttpServletRequest request, HttpServletResponse response) {
		return "/user/updateform.jsp";
	}
	private String signUp(HttpServletRequest request, HttpServletResponse response) {
		String path = "";
		String id,password,name,addressDefault,phoneNum,addressDetail,postNum,homeNum;
		id = request.getParameter("id");
		password = request.getParameter("password");
		name = request.getParameter("name");
		addressDefault = request.getParameter("address_default");
		phoneNum = request.getParameter("phoneNum");
		addressDetail = request.getParameter("address_detail");
		postNum = request.getParameter("postNum");
		homeNum = request.getParameter("homeNum");
		
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setAddressDefault(addressDefault);
		member.setPhonenum(phoneNum);
		member.setAddressDetail(addressDetail);
		member.setPostnum(postNum);
		member.setHomenum(homeNum);
		
		Member overlapCheck = memberService.searchById(member);
		if(overlapCheck != null) {
			// 중복된 아이디 존재
			path = "/user/signupfail.jsp";
		}else {
			memberService.signUp(member);
			path = "/user/signupsuccess.jsp";
		}
		return path;
	}
	private String login(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		Member member = new Member();
		member.setId(id);
		member.setPassword(password);
		
		Member loginMember = memberService.search(member);
		if(loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
		}else {
			request.setAttribute("errMsg", "아이디 또는 패스워드가 올바르지 않습니다.");
		}
		return "/main.jsp";
	}
	private String signUpForm(HttpServletRequest request, HttpServletResponse response) {
		return "/user/signupform.jsp";
	}
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/main.do";
	}
	private String loginForm(HttpServletRequest request, HttpServletResponse response) {
		return "/user/loginform.jsp";
	}
	
}
