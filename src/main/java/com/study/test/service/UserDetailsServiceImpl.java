package com.study.test.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.test.vo.MemberVO;

import jakarta.annotation.Resource;
import oracle.security.o3logon.a;

//UserDetailsService : 로그인 시 실행되는 메소드가 정의되어 있음.
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Resource(name = "memberService")
	private MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//입력한 아이디정보가 일치하는 회원 정보 조회
		MemberVO member = memberService.login(username);
		
		if(member == null) {
			//강제 오류 발생!!
			throw new BadCredentialsException("입력한 아이디를 가진 회원 없음");
		}
		
		//로그인 정보를 갖는 객체 생성
		//스프링 시큐리티를 사용하면 자동으로 로그인기능을 실행시켜준다
		//로그인 정브로르 UserDetails에 객체에 담아야한다.
		UserDetails userDetails = User.withUsername(member.getMemId())
										.password("{noop}" + member.getMemPw())		//{noop} : 조회된 비밀번호가 암호화 되지 않았다고 정의
										.roles(member.getRole().split(","))	//roles(String...a) : 배열
										.build();
		return userDetails;
	}

}
