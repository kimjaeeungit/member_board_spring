package com.kimjaeeun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kimjaeeun.vo.Member;

public interface MemberMapper {
	@Select("SELECT ID, PWD,EMAIL,NAME FROM TBL_MEMBER WHERE ID=#{id} AND PWD =#{pwd}")
	Member select(Member member);
	//회원가입
	@Insert("INSERT INTO TBL_MEMBER VALUES(#{id},#{pwd},#{email},#{name})")
	int insert(Member member);
	//회원수정
	@Update("UPDATE TBL_MEMBER SET PWD =#{pwd},EMAIL =#{email},NAME =#{name} WHERE ID =#{id}")
	int update(Member member);
	//회원탈퇴
	@Delete("DELETE FROM TBL_MEMBER WHERE ID=#{id}")
	int delete(String id);
	// id로 select
	@Select("SELECT ID,PWD,EMAIL,NAME FROM TBL_MEMBER WHERE ID=#{id}")
	Member findBy(String id);
	//회원목록조회
	@Select("SELECT ID,PWD,EMAIL,NAME FROM TBL_MEMBER")
	List<Member> selectMember();
	//id중복체크
	@Select("SELECT COUNT(*) FROM TBL_MEMBER WHERE ID=#{id}")
	int idChk(String id);
}
