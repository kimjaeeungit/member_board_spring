package com.kimjaeeun.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//vo

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Data
@RequiredArgsConstructor
public class Member {
	@NonNull
	private String id;
	@NonNull
	private String pwd;
	private String email;
	private String name;
	


	
	//생성자,getter,setter,toString 만들기
	
	
}
