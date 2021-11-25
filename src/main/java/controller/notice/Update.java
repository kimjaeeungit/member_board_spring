package controller.notice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimjaeeun.service.BoardService;
import com.kimjaeeun.service.BoardServiceImpl;
import com.kimjaeeun.util.MyFileRenamePolicy;
import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/notice/update")
public class Update extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 페이지 번호와 글 번호를 가져온다.
		Long bno = Long.parseLong(req.getParameter("bno"));
		req.setAttribute("board",new BoardServiceImpl().get(bno));
		req.getRequestDispatcher("/WEB-INF/jsp/board/update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = "c:\\upload";//파일이 저장되는 위치
		String path=getPath();//업로드 날짜 구분
		
		File uploadPath= new File(saveDirectory + File.separator+path);//c:\\upload + /+210913
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		
		int maxPostSize=10*1024*1024;
		String encoding="utf-8";
		FileRenamePolicy policy=new MyFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(req, uploadPath.getAbsolutePath(), maxPostSize, encoding, policy);
		Enumeration<String> files =multi.getFileNames();//files에 여러 파일 이름이랑 확장명 넣음
		List<Attach> attachs =new ArrayList<>();
		while(files.hasMoreElements()) {
		String file= files.nextElement();//파일내용
		String uuid=multi.getFilesystemName(file);//파일명
		if(uuid == null)continue;
		String origin=multi.getOriginalFileName(file);//파일명 그대로
		Attach attach =new Attach(uuid,origin,null,path);
		attachs.add(attach);//list에 추가
		}
		String b = req.getParameter("bno");
		Long bno=Long.parseLong(b);
		String title = multi.getParameter("title");//제목 받아오기
		String content = multi.getParameter("content");//내용 받아오기
		String id= ((Member)req.getSession().getAttribute("member")).getId();//세션에서 id 받아오기
		
		Board board = new Board(bno,title,content);
		board.setAttachs(attachs);//board.List attachs에 넣기
		
		new BoardServiceImpl().modify(board);//글번호 가져오기
		
		resp.sendRedirect("list");//글작성 후 list.jsp로 이동
		
	}
	
	private String getPath() {
		//현재시간 정보를 getPath()로 가져옴
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}
}
