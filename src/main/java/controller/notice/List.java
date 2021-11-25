package controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimjaeeun.service.BoardService;
import com.kimjaeeun.service.BoardServiceImpl;
import com.kimjaeeun.vo.Criteria;
import com.kimjaeeun.vo.PageDTO;

@WebServlet("/notice/list")
public class List extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService service=new BoardServiceImpl();
		
		String pn =req.getParameter("pageNum"); //페이지넘버 받아옴
		String am = req.getParameter("amount"); //리스트에 글 몇개 나타낼지 
		if(pn==null) pn="1"; //만약 페이지넘버가 널이면 1페이지
		if(am==null) am = "10"; //만약 리스트 글 갯수가 널이면 10개로
		Criteria cri =new Criteria(Integer.parseInt(pn),Integer.parseInt(am));
		req.setAttribute("list",service.list(cri));
		req.setAttribute("page", new PageDTO(service.getCount(cri),cri));
		req.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp").forward(req, resp);
		
	}
	
}
