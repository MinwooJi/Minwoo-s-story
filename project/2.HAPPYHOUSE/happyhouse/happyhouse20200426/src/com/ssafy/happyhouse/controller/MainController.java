package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dao.HouseDAO;
import com.ssafy.happyhouse.model.dao.HouseDAOImpl;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseDealLab;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.service.HouseDealLabService;
import com.ssafy.happyhouse.model.service.HouseDealLabServiceImpl;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.MemberServiceImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HouseService service;
	HouseDealLabService hdlService;
	HouseDAO dao;
	MemberService memberService;

	public MainController() {
		this.service = new HouseServiceImpl();
		hdlService = new HouseDealLabServiceImpl();
		this.dao = new HouseDAOImpl();
		memberService = new MemberServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		String act = request.getParameter("act");
		System.out.println(act);
		try {
			switch (act == null ? "" : act) {
			case "login":
				path = login(request, response);
				break;
			case "logout":
				path = logout(request, response);
				break;
			case "signupform":
				path = signUpForm(request, response);
				break;
			case "signup":
				path = signUp(request, response);
				break;
			case "memberinfo":
				path = memberInfo(request, response);
				break;
			case "list":
				path = list(request, response);
				break;
			case "detail":
				path = detail(request, response);
				break;
			case "memberupdateform":
				path = memberUpdateForm(request, response);
				break;
			case "updatemember":
				path = updateMember(request, response);
				break;
			case "deletemember":
				path = deleteMember(request, response);
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
			case "lab":
				path = lab(request, response);
				break;
			case "notice":
				path = notice(request, response);
				break;
			case "noticeDetail":
				path = noticeDetail(request, response);
				break;
			default:
				path = "/main.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			path = "/error.jsp";
			request.setAttribute("errMsg", e.getMessage());
		}

		if (path.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + path.substring("redirect:".length()));
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	private String noticeDetail(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		Notice notice = service.noticeDetail(no);
		request.setAttribute("notice", notice);
		return "/noticedetail.jsp";
	}

	private String notice(HttpServletRequest request, HttpServletResponse response) {
		List<Notice> noticeList = service.searchNotice();
		request.setAttribute("noticeList", noticeList);
		
		return "/noticelist.jsp";
	}

	private String findpwd(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phoneNum");
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setPhone(phone);
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
		return "findpwdresult.jsp";
	}

	private String findpwdform(HttpServletRequest request, HttpServletResponse response) {
		return "/findpwdform.jsp";
	}
	private String setnewpwdform(HttpServletRequest request, HttpServletResponse response) {
		return "/setnewpwdform.jsp";
	}
	private String setnewpwd(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String password1 = request.getParameter("pw1");
		String password2 = request.getParameter("pw2");
		String path = "";
		System.out.println(password1);
		System.out.println(password2);
		if(password1.equals(password2)) {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("memberInfo");
			System.out.println("찾는아이디"+member.getId());
			member = memberService.searchById(member);
			member.setPassword(password1);
			System.out.println(member);
			memberService.updateMember(member);
			session.invalidate();
			return "/setnewpwdsuccess.jsp";
		}else {
			request.setAttribute("errMsg", "비밀번호를 확인해 주세요.");
			return "/setnewpwdform.jsp";
		}
	}
	private String lab(HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		String searchType = request.getParameter("searchType");
		searchType = searchType == null ? "all" : searchType;
		String searchWord = request.getParameter("searchWord");
		HousePageBean bean = new HousePageBean();
		if (searchType.equals("all") == false) {
			if (searchType.equals("dong"))
				bean.setDong(searchWord);
			else
				bean.setAptname(searchWord);
		}
		List<HouseDealLab> list = hdlService.process(bean);
		request.setAttribute("list", list);
		return "/lab.jsp";
	}

	private String deleteMember(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Member member = (Member)request.getSession().getAttribute("loginMember");
		String id = member.getId();
		memberService.deleteMember(id);
		return logout(request, response);
	}

	private String updateMember(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setAddress(request.getParameter("address1"));
		member.setPhone(request.getParameter("phone"));
		
		memberService.updateMember(member);
		addLoginMemberToSession(request, member);
		return "/memberinfo.jsp";
	}

	private String memberUpdateForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/memberupdateform.jsp";
	}

	private String memberInfo(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.setAttribute("loginMember", request.getSession().getAttribute("loginMember"));
		return "/memberinfo.jsp";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		Member m = new Member();
		m.setId(request.getParameter("id"));
		m.setPassword(request.getParameter("password"));
		Member loginMember = memberService.signIn(m);
		if (loginMember != null) {
			addLoginMemberToSession(request, loginMember);
		} else {
			request.setAttribute("errMsg", "아이디 또는 패스워드를 확인해 주십시오.");
		}
		return "/main.jsp";
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		return "redirect:/main.do";
	}

	private String signUpForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/signupform.jsp";
	}

	private void addLoginMemberToSession(HttpServletRequest request, Member m) {
		request.getSession().setAttribute("loginMember", m);
	}

	private String signUp(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		Member m = new Member();
		m.setId(request.getParameter("id"));
		m.setPassword(request.getParameter("password"));
		m.setName(request.getParameter("name"));
		m.setAddress(request.getParameter("address1"));
		m.setPhone(request.getParameter("phone"));

		if (memberService.signUp(m)) {
			addLoginMemberToSession(request, m);
			return "redirect:/main.do";
		} else {
			request.getSession().setAttribute("errMsg", "회원 가입 중 문제가 발생했습니다.");
			/*
			 * 코딩하며 현재 여기 올 수 있는 가능성 1. db에서 pk인 id가 중복됨(이미 존재하는 아이디)
			 */
			return "/error.jsp";
		}
	}

	private String list(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String searchType = request.getParameter("searchType");
		searchType = searchType == null ? "all" : searchType;
		String searchWord = request.getParameter("searchWord");
		HousePageBean bean = new HousePageBean();
		bean.setSearchType(searchType);
		if (searchType.equals("all") == false) {
			if (searchType.equals("dong"))
				bean.setDong(searchWord);
			else
				bean.setAptname(searchWord);
		}
		String param = request.getParameter("pageNo");
		int pageNo;
		if(param == null)
			pageNo = 1;
		else
			pageNo = Integer.parseInt(param);
		bean.setPageNo(pageNo);

		List<HouseDeal> list = service.searchAll(bean);
		request.setAttribute("list", list);
		List<Integer> idxList = new ArrayList<>();
		int startIdx = 10 * (pageNo / 10);
		int lastIdx = (dao.getSize(bean) / 10) + 1;
		int lastPage = (lastIdx / 10) * 10;
		for(int i = startIdx; i < startIdx + 10; i++)
		{
			if(i == 0)
				continue;
			idxList.add(i);
			if(i >= lastIdx)
				break;
		}
		request.setAttribute("bean", bean);
		request.setAttribute("idxlist", idxList);
		request.setAttribute("nextIdx", startIdx + 10);
		request.setAttribute("prevIdx", startIdx - 1);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("lastIdx", lastIdx);
		return "/list.jsp";
	}

	private String detail(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		HouseDeal deal = service.search(no);
		request.setAttribute("deal", deal);
		return "/detail.jsp";
	}

}
