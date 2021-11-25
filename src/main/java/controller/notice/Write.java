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

import com.kimjaeeun.service.BoardServiceImpl;
import com.kimjaeeun.util.MyFileRenamePolicy;
import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/notice/write")
public class Write extends HttpServlet{

	//글쓰기 폼
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/board/write.jsp").forward(req, resp);
		
	}

	//글쓰기 후 프로세스
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//글 제목
		//글 내용
		//작성자
		
		//선행작업
		//session의 member이 null에 대한 예외처리
		String saveDirectory = "c:\\upload";//파일이 저장되는 위치
		String path=getPath();//업로드 날짜 구분
		
		File uploadPath= new File(saveDirectory + File.separator+path);//c:\\upload + /+210913
		if(!uploadPath.exists()) {//만약 uploadPath디렉토리가 없으면
			uploadPath.mkdirs();//디렉토리를 생성하겠다
		}
		
		
		int maxPostSize=10*1024*1024; //10mb로 파일 크기 제한
		String encoding="utf-8";
		//cos.jar:FileRenamePolicy,MultipartRequest
		//FileRenamePolicy:똑같은 이름을 가진 파일이 존재할 때 사용자가 만든 이름을 적용시킬 수 있게 해주는 클래스
		FileRenamePolicy policy=new MyFileRenamePolicy();
		//MultipartRequest를 통해 파일 업로드
		//request,파일저장경로,용량,인코딩타입,중복파일명에 대한 정책
		//getAbsolutePath(): 프로그램을 실행한 경로 함께 반환 (현재 경로+입력된 경로)
		MultipartRequest multi = new MultipartRequest(req, uploadPath.getAbsolutePath(), maxPostSize, encoding, policy);
		//System.out.println("multi"+multi);
		//Enumeration: 객체들을 집합체 형태로 관리해주는 인터페이스
		//여러파일 올릴때
		//getFileNames() : 파일경로의 파일이름 및 확장명 반환
		Enumeration<String> files =multi.getFileNames();//files에 여러 파일 이름이랑 확장명 넣음
		List<Attach> attachs =new ArrayList<>();
		//hasMoreElements() : 읽어올 요소가 남아있는지 확인
		while(files.hasMoreElements()) {//만약 요소가 남아있으면
		//nextElement()는 현재 커서가 가리키고 있는 데이터를 리턴하고 커서 다음칸으로 옮김
		String file= files.nextElement();//파일내용
		//getFilesystemName(file) : 모듈 내부에서 업로드한 파일명 얻어오는 함수
		String uuid=multi.getFilesystemName(file);//파일명
		if(uuid == null)continue;//uuid가 null이면 while문 탈출
		//getOriginalFileName() : 유저가 업로드한 파일명을 그대로 얻어오는 함수
		String origin=multi.getOriginalFileName(file);//파일명 그대로
		Attach attach =new Attach(uuid,origin,null,path);//초기화
		attachs.add(attach);//list에 추가
		}
		attachs.forEach(System.out::println);
	
		String title = multi.getParameter("title");//제목 받아오기
		String content = multi.getParameter("content");//내용 받아오기
		String id= ((Member)req.getSession().getAttribute("member")).getId();//세션에서 id 받아오기
		
		Board board = new Board(title,content,id,1L); //1(category)번이 게시글
		board.setAttachs(attachs);//board.List attachs에 넣기
		
		new BoardServiceImpl().register(board);//글번호 가져오기
		
		resp.sendRedirect("list");//글작성 후 list.jsp로 이동
	}
	
	private String getPath() {
		//현재시간 정보를 getPath()로 가져옴
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}
	
}
