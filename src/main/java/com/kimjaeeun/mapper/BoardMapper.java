package com.kimjaeeun.mapper;

import java.util.List;

import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Criteria;


public interface BoardMapper {
	//1.글작성o
	void insertSelectKey(Board board);
	//첨부파일o
	void writeAttach(Attach attach);
	// 2.글수정o
	int update(Board board);
	// 3.단일조회o
	Board read(Long bno);
	// 4.목록조회o
	List<Board> getList(Criteria cri);
	// 5.글삭제o
	int deleteBoard(Long bno);
	// 6.첨부파일 삭제o
	void deleteAttach(Long bno);
	// 7.댓글삭제o
	void deleteReply(Long bno);
	// 8.삭제할 파일명 가져오기o
	String getFileName(Long bno);
	// 9.다른날짜 사진 삭제o
	List<Attach> readAttachByPath(String path);
	// 10.첨부파일 목록만 가져오는 셀렉트 코드o
	List<Attach> readAttach(Long bno);
	// 11.원래 파일이름 o
	String findOriginBy(String uuid);
	// 12.글갯수 가져오기 o
	int getCount(Criteria cri);
	// 13.id로 정보 select하는 메서드o
	List<Board> findById(String id);
	// 14.탈퇴한회원 게시글 아이디 수정o
	void updateWriter(String id);
}
