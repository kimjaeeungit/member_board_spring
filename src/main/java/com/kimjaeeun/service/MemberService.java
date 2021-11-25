package com.kimjaeeun.service;

import java.util.List;

import com.kimjaeeun.vo.Member;

public interface MemberService {
	//할일
	
	//로그인
	boolean login(Member member);
	
	//회원가입
	void join(Member member);
	
	//회원정보수정
	void modify(Member member);
	
	//아이디로 회원 조회
	Member findBy(String id);
	
	//회원 목록 조회
	List<Member>getMembers();

	//회원탈퇴
	int deleteMember(String id, String pw);

	//id중복체크
	int idChk(String id);

	
}
