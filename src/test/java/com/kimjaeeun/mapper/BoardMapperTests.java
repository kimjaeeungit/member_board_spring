package com.kimjaeeun.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter @Autowired
	private BoardMapper boardmapper;
	//1.게시글 작성하기
	@Test
	public void testInsert(){
		Board board =  new Board();
		board.setTitle("영속 테스트 제목dd");
		board.setContent("영속 테스트 내용dd");
		board.setId("kje");
		boardmapper.insertSelectKey(board);
		log.info(board);
	}
	//2.게시글 수정
	@Test
	public void testUpdate(){
		Board board =  new Board();
		board.setTitle("수정 영속 테스트 제목 ");
		board.setContent("수정영속 테스트 내용");
		board.setId("kje");
		board.setCategory(1L);
		board.setBno(525L);
		boardmapper.update(board);
		log.info(boardmapper.read(525L));
	}
	//3.단일조회
	@Test
	public void testRead(){
		log.info(boardmapper.read(525L));
	}
	//4.목록조회
	@Test
	public void tetGetListPaging() {
		Criteria cri =  new Criteria(); //기본생성자(1,10)
		boardmapper.getList(cri).forEach(log::info);//1,10,T,수정
	}
	//5.글삭제
	@Test
	public void testDeleteBoard(){
		log.info(boardmapper.read(525L));
		boardmapper.deleteBoard(525L);
		log.info(boardmapper.read(525L));
	}
	//7.댓글삭제
	@Test
	public void testDeleteReply(){
	   boardmapper.deleteReply(460L);
	}
	//8.삭제할 파일명 가져오기
	@Test
	public void testGetFileName(){
		log.info(boardmapper.getFileName(457L));
	}
	//9.다른날짜 사진 삭제
	@Test
	public void testReadAttachByPath(){
		log.info(boardmapper.readAttachByPath("211026"));
	}
	//10.첨부파일 목록만 가져오는 셀렉트 코드
	@Test
	public void testReadAttach(){
		log.info(boardmapper.readAttach(457L));
	}
	//11.원래 파일이름
	@Test
	public void testFindOriginBy(){
		log.info(boardmapper.findOriginBy("098c2af5-552a-4aa8-a9a2-671985b6a95e.png"));
	}
	//12.글갯수 가져오기
	@Test
	public void testGetCount(){
		Criteria cri =  new Criteria();
		log.info(boardmapper.getCount(cri));
	}
	//13.id로 정보 select하는 메서드
	@Test
	public void getFindById(){
		boardmapper.findById("kje").forEach(log::info);//1,10,T,수정
	}
	//14.탈퇴한회원 게시글 아이디 수정
	@Test
	public void testUpdateWriter(){
		boardmapper.updateWriter("admin2");
	}
}

