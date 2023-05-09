package com.study.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberVO {
	private String memId;
	private String memPw;
	private String memName;
	private String role;
}
