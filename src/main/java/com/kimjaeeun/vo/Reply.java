package com.kimjaeeun.vo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reply {
	private Long rno;
	private String title;
	private String content;
	private String regDate;
	private String id;
	private Long bno;
	
	public Reply(Long rno, String title, String content, String regDate, String id, Long bno) {
		super();
		this.rno = rno;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.id = id;
		this.bno = bno;
	}

	public Reply(Long rno, String title, String content, String id, Long bno) {
		super();
		this.rno = rno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.bno = bno;
	}
	
	
}

