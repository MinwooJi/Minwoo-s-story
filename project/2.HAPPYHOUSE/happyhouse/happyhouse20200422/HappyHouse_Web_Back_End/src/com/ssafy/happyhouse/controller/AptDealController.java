package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.Search;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;
import com.ssafy.happyhouse.util.PageNavigation;

@WebServlet("/apt.do")
public class AptDealController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int currentPage = 1;
	private HouseService service;
	
	public AptDealController() {
		service = new HouseServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = "";
		String act = request.getParameter("act");
		try {
			switch (act == null ? "" : act) {
			case "list":
				path = list(request, response);
				break;
			case "detail":
				path = detail(request, response);
				break;
			default: 
				path = "/index.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			path = "/apt/aptError.jsp";
			request.setAttribute("errMsg", "아파트 정보 처리시 에러가 발생했습니다");
		}
		
		if (path.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + path.substring("redirect:".length()));
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	private String list(
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 페이지 번호가 넘어오지 않은 경우 때문에 1로 초기화
		currentPage = 1;
		String s = request.getParameter("pg");
		if(s != null && s.trim().length() != 0) {
			// 페이징
			currentPage = Integer.parseInt(s); // 현재 페이지 번호 
		} 
		System.out.println(currentPage);
		String spp = request.getParameter("spp");
		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);// 페이지당 글 개수
		
		// 검색 종류
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		Search param = new Search();
		param.setSearchType(searchType == null ? "all" : searchType);
		param.setSearchWord(searchWord);
		System.out.println("Controller, " + param.getSearchType() + " " + param.getSearchWord() + " !");
		
		List<HouseDeal> list = service.list(currentPage, sizePerPage, param);
		for(HouseDeal hd : list) {
			System.out.println(hd);
		}
		PageNavigation pageNavigation = service.makePageNavigation(currentPage, sizePerPage, searchWord, param.getSearchType());
		
		request.setAttribute("list", list);
		request.setAttribute("navigation", pageNavigation);
//		request.setAttribute("sType", param.getSearchType());
//		request.setAttribute("sWord", searchWord);
		return "/apt/aptDeal.jsp";
	}
	
	private String detail(
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		String aptName = request.getParameter("aptName");
		HouseDeal hd = service.detail(aptName);
		
		request.setAttribute("apt", hd);
		return "/apt/aptDetail.jsp";
	}

}
