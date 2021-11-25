package com.kimjaeeun.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//javabean만들기
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attach {
	//UUID 
	//Origin
	//bno
	private String uuid; //파일명
	private String origin; //파일명 그대로
	private Long bno; //글번호
	private String path; //날짜파일
}
