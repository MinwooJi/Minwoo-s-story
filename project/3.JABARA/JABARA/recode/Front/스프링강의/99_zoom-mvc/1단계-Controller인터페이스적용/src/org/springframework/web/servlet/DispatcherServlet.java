package org.springframework.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.controller.DeleteBoardController;
import com.ssafy.board.controller.DetailBoardController;
import com.ssafy.board.controller.ListBoardController;


public class DispatcherServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String requestUri = request.getRequestURI();
		System.out.println("호출됨....");
		String contextPath=request.getContextPath();
		System.out.println(requestUri.substring(contextPath.length()));
		requestUri=requestUri.substring(contextPath.length());
		try {
			switch(requestUri) {
			case"/board/list.do" : //게시물 목록 처리
				ListBoardController list= new ListBoardController();
				list.service(request, response);
				break;
			case"/board/detail.do" : //게시물 목록 처리
				DetailBoardController detail =new DetailBoardController();
				detail.service(request, response);
				break;
			case"/board/delete.do" : //게시물 목록 처리
				DeleteBoardController delete =new DeleteBoardController();
				delete.service(request, response);
				break;
			case"/board/writeform.do" : //게시물 목록 처리
				System.out.println("등록폼 처리 요청");
				break;
			case"/board/write.do" : //게시물 목록 처리
				System.out.println("등록 처리 요청");
				break;
			}	
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
}
