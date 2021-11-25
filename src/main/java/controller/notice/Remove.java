package controller.notice;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimjaeeun.dao.BoardDao;
import com.kimjaeeun.service.BoardService;
import com.kimjaeeun.service.BoardServiceImpl;
import com.kimjaeeun.vo.Board;

@WebServlet("/notice/remove")
public class Remove extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//글번호 가져오기
		String b = req.getParameter("bno");
		Long bno=Long.parseLong(b);
		BoardDao dao=new BoardDao();
		Board vo = new Board();
		BoardService service=new BoardServiceImpl();
		//삭제할 글에 있는 파일 정보 가져오기
		String fileName=service.getFileName(bno);
		//댓글 삭제
		service.deleteReply(bno);
		//파일 삭제
		if(fileName != null) {
			dao.deleteAttach(bno);
		}
		//글 삭제
		boolean result = service.remove(bno);
		
		resp.sendRedirect("list");
		
	}
	
}
