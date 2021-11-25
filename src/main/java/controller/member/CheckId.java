package controller.member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimjaeeun.dao.MemberDao;
import com.kimjaeeun.service.BoardServiceImpl;
import com.kimjaeeun.vo.Member;

@WebServlet("/checkid")
public class CheckId extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		req.setAttribute("id",id);
		resp.sendRedirect("index.html");
	}


	
}
