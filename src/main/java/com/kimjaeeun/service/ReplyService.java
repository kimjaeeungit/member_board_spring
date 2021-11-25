package com.kimjaeeun.service;

import java.util.List;

import com.kimjaeeun.vo.Reply;

public interface ReplyService {
	// 댓글쓰기 자바스크릡트 이용한 비동기처리

	// 댓글목록
	List<Reply> list(Long bno);

	// 댓글 단일 조회
	Reply get(Long rno);

	// 댓글 삭제
	void remove(Long rno);

	// 댓글 쓰기
	void write(Reply reply);

}
