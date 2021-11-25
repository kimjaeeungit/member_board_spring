package com.kimjaeeun.mapper;


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
public class MemberMapperTests {
	@Autowired @Setter
	private MemberMapper memberMapper;
	
	//회원조회 테스트
	@Test
	public void testSelectMember(){
		memberMapper.selectMember().forEach(System.out:: println);
	}
	//로그인 테스트
	@Test
	public void testSelect(){
		Member vo = new Member("admin", "12345");
		Member member = memberMapper.select(vo);
		log.info(vo);
		log.info(member);
	}
	//회원가입 테스트
	@Test
	public void testInsert(){
		Member vo = new Member("kje2", "1234","dmjf1223@naver.com","김재은");
		int count = memberMapper.insert(vo);
		log.info(vo);
		log.info(count);
	}
	//회원정보수정 테스트
	@Test
	public void testUpdate(){
		Member vo = new Member();
		vo.setId("kje2");
		vo.setPwd("12345");
		vo.setEmail("sada@dasd");
		vo.setName("수정입니다");
		int count = memberMapper.update(vo);
		log.info(count);
	}
	//회원탈퇴 테스트
	@Test
	public void testDelete(){
		int modify = memberMapper.delete("kje2");
		log.info(modify);
	}
	// id로 select
	@Test
	public void testFindBy(){
		Member vo = new Member();
		Member member = memberMapper.findBy("kje");
		log.info(member);
	}
	//id중복체크
	@Test
	public void testIdChk(){
		int count=memberMapper.idChk("kje");
		log.info("숫자"+count);
	}
	
}
