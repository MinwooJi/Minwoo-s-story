package org.springframework.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DispatcherServlet extends HttpServlet{
	private URLHandlerMapping uhm;
	public void init(ServletConfig config) {
		uhm = new URLHandlerMapping();
	}
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String contextPath=request.getContextPath();
		String requestUri = request.getRequestURI().substring(contextPath.length());
		try {
			Controller control =uhm.getController(requestUri);
			if(control==null) {
				throw new ServletException("요청하신 URL이 존재하지 않습니다.");
			}
			control.service(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
}
