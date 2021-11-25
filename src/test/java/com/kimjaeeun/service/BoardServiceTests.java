package com.kimjaeeun.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter 
	@Autowired //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입
	private BoardService service;
	
	@Test
	public void testExist(){
		assertNotNull(service);//객체 service가 null이 아닌지 확인
	}
	// 1.글작성
	@Test
	public void testRegister(){
		Board board=new Board();
		board.setTitle("서비스 테스트 등록글 제목");
		board.setContent("서비스 테스트 등록글 내용");
		board.setId("kje");
		board.setCategory(1L);
		service.register(board);
	}
	// 2.글수정
	@Test
	public void testModify(){
		Board board=new Board();
		board.setTitle("서비스 테스트 등록글 제목 수정");
		board.setContent("서비스 테스트 등록글 내용 수정");
		board.setId("kje");
		board.setBno(527L);
		service.modify(board);
	}
	// 3.단일조회(상세보기)
	@Test
	public void testGet(){
		log.info(service.get(475L));
	}
	// 4.목록조회
	@Test
	public void testGetList(){
		service.list(new Criteria()).forEach(log::info);
	}
	// 5.글삭제  6.첨부파일삭제
	@Test
	public void testRemove(){
		service.remove(457L);
	}
	// 7.댓글삭제
	@Test
	public void testDeleteReply(){
		service.deleteReply(457L);
	}
	// 8.삭제할 파일명 가져오기
	@Test
	public void testGetFileName(){
		log.info(service.getFileName(457L));
	}
	// 9.다른날짜 사진 삭제
	@Test
	public void testReadAttachByPath(){
		log.info(service.readAttachByPath("path"));
	}
	// 10.첨부파일 목록만 가져오는 셀렉트 코드
	@Test
	public void testReadAttach(){
		service.readAttach(457L).forEach(log::info);
	}
	// 11.원래 파일이름
	@Test
	public void testFindOriginBy(){
		log.info(service.findOriginBy("uuid"));
	}
	// 12.글갯수
	@Test
	public void testGetCount(){
		log.info(service.getCount(new Criteria()));
	}
	// 13.id로 정보 select하는 메서드
	@Test
	public void testFindById(){
		service.findById("kje").forEach(log::info);
	}
	// 14.탈퇴한회원 게시글 아이디 수정
	@Test
	public void testUpdateWriter(){
		service.updateWriter("kje");
	}

}
