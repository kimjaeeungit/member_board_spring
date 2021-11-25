package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findpwd")
public class FindPwd extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//그냥 웹브라우저 통해서 실행하면 join.jsp로 이동
		req.getRequestDispatcher("WEB-INF/jsp/member/findpwd.jsp").forward(req,resp);
	}
	
}
