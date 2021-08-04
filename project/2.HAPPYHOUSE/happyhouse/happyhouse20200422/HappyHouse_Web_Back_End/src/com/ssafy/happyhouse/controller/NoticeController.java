package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.AdminDto;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.AdminLoginService;
import com.ssafy.happyhouse.model.service.AdminLoginServiceImpl;
import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.happyhouse.model.service.NoticeServiceImpl;
import com.ssafy.happyhouse.util.PageNavigation;


@WebServlet("/notice.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminLoginService loginService;
	private NoticeService noticeService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new AdminLoginServiceImpl();
		noticeService = new NoticeServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		System.out.println(act);
		if("mvlogin".equals(act)) {
			response.sendRedirect(root + "/notice/login.jsp");
		} else if("mvjoin".equals(act)) {
			response.sendRedirect(root + "/notice/join.jsp");
		} else if("login".equals(act)) {
			login(request, response);
		} else if("logout".equals(act)) {
			logout(request, response);
		} else if("mvwrite".equals(act)) {
			response.sendRedirect(root + "/notice/write.jsp");
		} else if("write".equals(act)) {
			writeNotice(request, response);
		} else if("list".equals(act)) {
			listNotice(request, response);
		} else if("mvmodify".equals(act)) {
			moveModifyNotice(request, response);
		} else if("modify".equals(act)) {
			modifyNotice(request, response);
		} else if("delete".equals(act)) {
			deleteNotice(request, response);
		} else if("listNoticeByAlgo".equals(act)) {
			listNoticeByAlgo(request, response);
		} else {
			response.sendRedirect(root + "/notice/login.jsp");
		}
	}

	private void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/notice/noticeMain.jsp";
		int noticeno = Integer.parseInt(request.getParameter("noticeno"));
		
		try {
			noticeService.deleteNotice(noticeno);
			path = "/notice.do?act=list&key=&word=";
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글삭제 처리 중 문제가 발생했습니다.");
			path = "/notice/noticeError.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	private void modifyNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/notice/noticeMain.jsp";
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setNoticeno(Integer.parseInt(request.getParameter("noticeno")));
		noticeDto.setSubject(request.getParameter("subject"));
		noticeDto.setContent(request.getParameter("content"));
		
		try {
			noticeService.modifyNotice(noticeDto);
			path = "/notice.do?act=list&key=&word=";
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글작성중 문제가 발생했습니다.");
			path = "/notice/noticeError.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	private void moveModifyNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/notice/noticeMain.jsp";
		int noticeno = Integer.parseInt(request.getParameter("noticeno"));
		
		try {
			NoticeDto noticeDto = noticeService.getNotice(noticeno);
			request.setAttribute("notice", noticeDto);
			path = "/notice/modify.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글수정 처리 중 문제가 발생했습니다.");
			path = "/notice/noticeError.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void listNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/notice/noticeMain.jsp";
		String temp = request.getParameter("pg");
		int currentPage = Integer.parseInt( (temp==null || temp.trim().length()==0 ) ? "1" : request.getParameter("pg"));// 현재페이지 번호
		String spp = request.getParameter("spp"); // 한페이지당 글갯수
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
		
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		try {
//			List<NoticeDto> list = noticeService.listNotice(key, word);
			List<NoticeDto> list = noticeService.listNotice(currentPage, sizePerPage, key, word);
			PageNavigation pageNavigation = noticeService.makePageNavigation(currentPage, sizePerPage, key, word);
			request.setAttribute("notices", list);
			request.setAttribute("navigation", pageNavigation);
			path = "/notice/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록을 얻어오는 중 문제가 발생했습니다.");
			path = "/notice/noticeError.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	private void listNoticeByAlgo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/notice/noticeMain.jsp";
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		try {
			List<NoticeDto> list = noticeService.listNotice(key, word);
			request.setAttribute("notices", list);
			path = "/notice/listByAlgo.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "글목록을 얻어오는 중 문제가 발생했습니다.");
			path = "/notice/noticeError.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void writeNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/notice/noticeMain.jsp";
		HttpSession session = request.getSession();
		AdminDto adminDto = (AdminDto) session.getAttribute("userinfo");
		if(adminDto != null) {
			NoticeDto noticeDto = new NoticeDto();
			noticeDto.setUserid(adminDto.getUserid());
			noticeDto.setSubject(request.getParameter("subject"));
			noticeDto.setContent(request.getParameter("content"));
			
			try {
				noticeService.writeNotice(noticeDto);
				path = "/notice/writesuccess.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "공지사항 작성중 문제가 발생했습니다.");
				path = "/notice/noticeError.jsp";
			}
		} else {
			request.setAttribute("msg", "관리자로 로그인 후 사용 가능한 페이지입니다.");
			path = "/notice/noticeError.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userinfo");
		response.sendRedirect(request.getContextPath());
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/notice/noticeMain.jsp";
		//변경해야 할부분
		String adminid = request.getParameter("userid");
		String adminpwd = request.getParameter("userpwd");
		System.out.println(adminid);
		System.out.println(adminpwd);
		
		try {
			AdminDto adminDto = loginService.login(adminid, adminpwd);
			if(adminDto != null) {
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", adminDto);
				
//				cookie 설정
				String idsave = request.getParameter("idsave");
				if("saveok".equals(idsave)) {//아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("admin_id", adminid);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 3);//3년간 저장.
					response.addCookie(cookie);
				} else {//아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("admin_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
			} else {
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 로그인해 주세요.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 문제가 발생했습니다.");
			path = "/notice/noticeError.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	public static void main(String[] args) {
		
	}
	
}
