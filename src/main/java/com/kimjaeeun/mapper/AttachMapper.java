package com.kimjaeeun.mapper;

import java.util.List;

import com.kimjaeeun.vo.Attach;


public interface AttachMapper {
	//첨부파일v
	void writeAttach(Attach attach);
	// 6.첨부파일 삭제
		void deleteAttach(Long bno);
}
