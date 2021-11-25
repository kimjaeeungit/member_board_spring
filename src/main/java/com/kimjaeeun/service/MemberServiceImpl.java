package com.kimjaeeun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kimjaeeun.mapper.MemberMapper;
import com.kimjaeeun.vo.Member;

import lombok.Setter;

@Service 
public class MemberServiceImpl implements MemberService {
	@Setter @Autowired
	private MemberMapper memberMapper;

	//로그인
	@Override
	public boolean login(Member member) {
		return memberMapper.select(member) != null;
	}
	
	//회원가입
	@Override
	public void join(Member member) {
		memberMapper.insert(member);
		
	}
	
	//회원정보수정
	@Override
	public void modify(Member member) {
		memberMapper.update(member);		
	}
	
	//회원탈퇴
	public int deleteMember(String id, String pw) {
		int x = -1;
		Member vo = new Member(id, pw);
		if(login(vo)) 
			return memberMapper.delete(id);
		return x;
	}
	
	// id로 select
	@Override
	public Member findBy(String id) {
		// TODO Auto-generated method stub
		return memberMapper.findBy(id);
	}
	
	@Override
	public List<Member>getMembers(){
		return memberMapper.selectMember();
	}
	//id중복체크
	@Override
	public int idChk(String id){
		return memberMapper.idChk(id);
	}
}
