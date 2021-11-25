package com.kimjaeeun.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.MidiDevice.Info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kimjaeeun.mapper.BoardMapper;
import com.kimjaeeun.service.BoardService;
import com.kimjaeeun.service.BoardServiceImpl;
import com.kimjaeeun.service.MemberService;
import com.kimjaeeun.util.CommonConst;
import com.kimjaeeun.util.MyFileRenamePolicy;
import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Criteria;
import com.kimjaeeun.vo.Member;
import com.kimjaeeun.vo.PageDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@AllArgsConstructor
@Log4j
public class BoardController {
	private BoardService service;
	
	@GetMapping("list")
	public void list(Model model,Criteria cri){
	model.addAttribute("list",service.list(cri));
	model.addAttribute("page", new PageDTO(service.getCount(cri),cri));
	
}
	@GetMapping("detail")
	public void detail(Model model,Long bno){
	model.addAttribute("board", service.get(bno));
	
	}
	//글쓰기 폼
	@GetMapping("write")
	public void writeForm(){}
	
	@PostMapping("write")
	public String write(HttpServletRequest req,HttpSession session) throws IOException{
		String saveDirectory = CommonConst.UPLOAD_PATH;//파일이 저장되는 위치
		String path=getPath();//업로드 날짜 구분
		
		File uploadPath= new File(saveDirectory + File.separator+path);//c:\\upload + /+210913
		if(!uploadPath.exists()) {//만약 uploadPath디렉토리가 없으면
			uploadPath.mkdirs();//디렉토리를 생성하겠다
		}
		
		
		int maxPostSize=10*1024*1024; //10mb로 파일 크기 제한
		String encoding="utf-8";
		FileRenamePolicy policy=new MyFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(req, uploadPath.getAbsolutePath(), maxPostSize, encoding, policy);
		Enumeration<String> files =multi.getFileNames();//files에 여러 파일 이름이랑 확장명 넣음
		List<Attach> attachs =new ArrayList<>();
		while(files.hasMoreElements()) {//만약 요소가 남아있으면
		String file= files.nextElement();//파일내용
		String uuid=multi.getFilesystemName(file);//파일명
		if(uuid == null)continue;//uuid가 null이면 while문 탈출
		String origin=multi.getOriginalFileName(file);//파일명 그대로
		Attach attach =new Attach(uuid,origin,null,path);//초기화
		attachs.add(attach);//list에 추가
		}
	
		String title = multi.getParameter("title");//제목 받아오기
		String content = multi.getParameter("content");//내용 받아오기
		String id= ((Member)session.getAttribute("member")).getId();//세션에서 id 받아오기
		
		Board board = new Board(title,content,id,1L); //1(category)번이 게시글
		board.setAttachs(attachs);//board.List attachs에 넣기
		log.info(board);
		service.register(board);//글번호 가져오기
		
		return "redirect:/board/list";
	}
	private String getPath() {
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}
	
	@GetMapping("update")
	public void update(Model model,Long bno){
	model.addAttribute("board", service.get(bno));
	
	}
	
/*	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 페이지 번호와 글 번호를 가져온다.
		Long bno = Long.parseLong(req.getParameter("bno"));
		req.setAttribute("board",new BoardServiceImpl().get(bno));
		req.getRequestDispatcher("/WEB-INF/jsp/board/update.jsp").forward(req, resp);
	}
	*/
	@PostMapping("update")
	public void update(HttpServletRequest req,HttpServletResponse resp,HttpSession session) throws ServletException, IOException{
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
		
		Board board = new Board(bno,title,content,id);
		board.setAttachs(attachs);//board.List attachs에 넣기
		
		service.modify(board);//글번호 가져오기
		
		resp.sendRedirect("list");//글작성 후 list.jsp로 이동
	}
	
	@GetMapping("remove")
	public void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//글번호 가져오기
		String b = req.getParameter("bno");
		Long bno=Long.parseLong(b);
		//글 삭제,첨부파일 삭제,댓글 삭제
		service.remove(bno);
		resp.sendRedirect("list");
	}
}
