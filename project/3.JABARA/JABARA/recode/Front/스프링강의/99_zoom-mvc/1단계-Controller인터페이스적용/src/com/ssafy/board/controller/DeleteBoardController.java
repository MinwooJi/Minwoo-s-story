package com.ssafy.board.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.service.BoardService;
import com.ssafy.board.service.BoardServiceImpl;
import com.ssafy.board.vo.Board;

public class DeleteBoardController implements Controller{
	private BoardService service;
	public DeleteBoardController() {
		service= new BoardServiceImpl();
	}
	public void service(HttpServletRequest request, HttpServletResponse response)throws Exception {
		//파라미터 추출
		int no = Integer.parseInt(request.getParameter("no"));
		
		// 작업할 내용
		service.delete(no);
		
		//페이지 이동
		response.sendRedirect("list.do");
		
	}
}
