package com.study.test.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.test.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	//로그인(기존 방식)
	@Override
	public MemberVO originLogin(MemberVO memberVO) {
		return sqlSession.selectOne("memberMapper.originLogin", memberVO);
	}

	//로그인(새로운 방식)
	@Override
	public MemberVO login(String memId) {
		return sqlSession.selectOne("memberMapper.login", memId);
	}
	
}
