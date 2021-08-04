package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {
	private BoardService service = new BoardServiceImpl();
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			Board board = service.detail(no);
			
			PrintWriter out = response.getWriter();
			// 조회된 객체의 내용을 json 문자열로 만들어서 ajax 객체에 전송
			out.println( new Gson().toJson(board) );  // {}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
