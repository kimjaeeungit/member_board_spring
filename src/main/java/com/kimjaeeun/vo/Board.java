package com.kimjaeeun.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class Board { 
	private Long bno; 
	private String title; 
	private String content; 
	private Date regDate;
	private String id;
	private Long category;//1:보드글 2:갤러리글
	
	private List<Attach>attachs= new ArrayList<Attach>(); //첨부파일
	//private List<Attach>attachs;//첨부파일
	private Integer replyCnt;
	

	public Board(String title, String content, String id, Long category) {
		super();
		this.title = title;
		this.content = content;
		this.id = id;
		this.category = category;
	}

	public Board(Long bno, String title, Date regDate, String id, Long category) {
		super();
		this.bno = bno;
		this.title = title;
		this.regDate = regDate;
		this.id = id;
		this.category = category;
	}

	public Board(Long bno, String title, String content) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
	}

	public Board(Long bno, String title, String content, Date regDate, String id, Long category) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.id = id;
		this.category = category;
	}

	public Board(Long bno, String title, String content, String id) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.id = id;
	}	
	
	
	
	
}
