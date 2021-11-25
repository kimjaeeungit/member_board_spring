package com.kimjaeeun.service;

import java.util.List;

import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Criteria;

public interface BoardService {
	// 1.글작성
	Long register(Board board);
	//첨부파일
	// 2.글수정
	boolean modify(Board board);
	// 3.단일조회
	Board get(Long bno);
	// 4.목록조회
	List<Board> list(Criteria cri);
	// 5.글삭제
	boolean remove(Long bno);
	// 첨부파일 삭제
	// 6.댓글삭제
	void deleteReply(Long bno);
	// 7.삭제할 파일명 가져오기
	String getFileName(Long bno);
	// 8.다른날짜 사진 삭제o
	List<Attach> readAttachByPath(String path);
	// 9.첨부파일 목록만 가져오는 셀렉트 코드o
	List<Attach> readAttach(Long bno);
	// 10.원래 파일이름 o
	String findOriginBy(String uuid);
	// 11.글갯수
	int getCount(Criteria cri);
	// 12.id로 정보 select하는 메서드o
	List<Board> findById(String id);
	// 13.탈퇴한회원 게시글 아이디 수정o
	void updateWriter(String id);
}
