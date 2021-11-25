package controller.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimjaeeun.service.BoardServiceImpl;

@WebServlet("/gallery/detail")
public class Detail extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		req.setAttribute("board",new BoardServiceImpl().get(bno));
		req.getRequestDispatcher("/WEB-INF/jsp/board/detail.jsp").forward(req, resp);
		
	}
	
}
