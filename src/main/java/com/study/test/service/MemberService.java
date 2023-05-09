package com.study.test.service;

import com.study.test.vo.MemberVO;

public interface MemberService {
	
	//로그인(기존방식)
	public MemberVO originLogin(MemberVO memberVO);

	//로그인(새로운 방식)
	public MemberVO login(String memId);
}
