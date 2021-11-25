package com.kimjaeeun.service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kimjaeeun.dao.ReplyDao;
import com.kimjaeeun.mapper.BoardMapper;
import com.kimjaeeun.vo.Attach;
import com.kimjaeeun.vo.Board;
import com.kimjaeeun.vo.Criteria;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService {
	@Setter
	@Autowired
	private BoardMapper boardMapper;

	// 1.글작성, 첨부파일
	@Override 
	@Transactional
	//첨부파일 추가해서 글작성
	public Long register(Board board) {
		//글 작성 후 글번호 반환
		System.out.println(board);
		System.out.println(boardMapper);
		boardMapper.insertSelectKey(board);
		//각 첨부파일에 글번호 부여
		for(Attach attach:board.getAttachs()) {
			attach.setBno(board.getBno());
			//첨부파일 작성
			boardMapper.writeAttach(attach);
		}		
		return board.getBno();
	}
	// 2.글수정	
	@Override @Transactional
	public boolean modify(Board board) {
		boolean result= boardMapper.update(board) >0;
		//첨부파일 다지우고
		boardMapper.deleteAttach(board.getBno());
		//첨부파일 다시 작성
	   if(result){
		   board.getAttachs().forEach(vo->{
			   vo.setBno(board.getBno());
			   boardMapper.writeAttach(vo);
		   });
	   }
	   return result;
	}
	// 3.단일조회
	@Override
	public Board get(Long bno) {
		Board board = boardMapper.read(bno);
		board.setAttachs(boardMapper.readAttach(bno));
		return board;
	}
	// 4.목록조회
	@Override
	public List<Board> list(Criteria cri) {
		System.out.println("boardMapper:"+boardMapper);
		List<Board>list=boardMapper.getList(cri);
		list.forEach(b->b.setAttachs(boardMapper.readAttach(b.getBno())));
		return list;
	}
    // 5.글삭제, 첨부파일삭제
    @Override @Transactional
    public boolean remove(Long bno) {
       String fileName=getFileName(bno);
       if(fileName != null) {
    	   boardMapper.deleteAttach(bno);
		}
       deleteReply(bno);
       return boardMapper.deleteBoard(bno) >0 ;
    }
	// 6.댓글삭제
	public void deleteReply(Long bno) {
		boardMapper.deleteReply(bno);
	}
	// 7.파일이름 가져오기
	public String getFileName(Long bno) {
		return boardMapper.getFileName(bno);
	}
	// 8.다른날짜 사진 삭제
	@Override
	public List<Attach> readAttachByPath(String path) {
		return boardMapper.readAttachByPath(path);
	}
	// 9.첨부파일 목록만 가져오는 셀렉트 코드
	@Override
	public List<Attach> readAttach(Long bno){
		return boardMapper.readAttach(bno);
	}
	// 10.원래 파일이름
	@Override
	public String findOriginBy(String uuid) {
		return boardMapper.findOriginBy(uuid);
	}
	// 11.글갯수
	@Override
	public int getCount(Criteria cri) {
		return boardMapper.getCount(cri);
	}
	// 12.id로 정보 select하는 메서드
	@Override
	public List<Board> findById(String id){
		return boardMapper.findById(id);
	}
	// 13.탈퇴한회원 게시글 아이디 수정
	@Override
	public void updateWriter(String id){
		boardMapper.updateWriter(id);
	}
	

	
}
