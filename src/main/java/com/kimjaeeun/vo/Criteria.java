package com.kimjaeeun.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

//자바빈 :기본생성자 ,게터세터

@Data
@AllArgsConstructor
public class Criteria {
	private int pageNum;
	private int amount;
	private int category=1;

	public Criteria() {
		this(1, 10);
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
}
