package com.kimjaeeun.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimjaeeun.vo.Member;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {
	@Setter 
	@Autowired //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입
	private MemberService service;
	
	@Test
	public void testExist(){
		assertNotNull(service);//객체 service가 null이 아닌지 확인
	}
	
	//회원목록
	@Test
	public void testGetMembers(){
		service.getMembers().forEach(log::info);
	}
	
	//로그인
	@Test
	public void testLogin(){
		Member member=new Member();
		member.setId("admin");
		member.setPwd("1234");
		log.info(service.login(member));
	}
	
	//회원가입
	@Test
	public void testJoin(){
		Member vo = new Member("kje2", "1234","dmjf1223@naver.com","김재은");
		service.join(vo);
	}
	
	//회원정보수정
	@Test
	public void testModify(){
		Member member = new Member();
		member.setPwd("12345666");
		member.setEmail("sada@dasd6");
		member.setName("수정입니다6");
		member.setId("kje");
		service.modify(member);
	}
	
	//회원탈퇴
	@Test
	public void testDelete(){
		service.deleteMember("kje2", "1234");
	}
	
	//findBy
	@Test
	public void testFindBy(){
		log.info(service.findBy("kje"));
	}
	
	//id중복체크
	@Test
	public void testIdChk(){
		log.info(service.idChk("kje"));
	}
	
}
