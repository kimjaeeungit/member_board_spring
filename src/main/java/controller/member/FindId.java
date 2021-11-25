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

@WebServlet("/findid")
public class FindId extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/jsp/member/findid.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		MemberDao dao= new MemberDao();
		Member member=dao.findById(email,name);
		String id=member.getId();
		req.setAttribute("id",id);
		req.getRequestDispatcher("WEB-INF/jsp/member/checkid.jsp").forward(req,resp);
	}
	
}
