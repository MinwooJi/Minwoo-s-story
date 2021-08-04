package com.ssafy.board.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.service.BoardService;
import com.ssafy.board.service.BoardServiceImpl;
import com.ssafy.board.vo.Board;

public class DetailBoardController implements Controller{
	private BoardService service;
	public DetailBoardController() {
		service= new BoardServiceImpl();
	}
	public void service(HttpServletRequest request, HttpServletResponse response)throws Exception {
		//파라미터 추출
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 작업할 내용
		Board board = service.detail(no);
		
		//데이터 공유
		request.setAttribute("board", board);
		
		//페이지 이동
		RequestDispatcher rd= request.getRequestDispatcher("/board/detail.jsp");
		rd.forward(request, response);
		
	}
}
