package com.study.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.test.service.MemberService;
import com.study.test.vo.MemberVO;

import jakarta.annotation.Resource;
import oracle.security.o3logon.a;

@Controller
@RequestMapping("/se")
public class SecurityController {
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@GetMapping("/page1")
	public String page1() {
		return "page1";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_page";
	}
	
	//이것도 SecurityController에서 권한을 줘야 로그인이 가능해짐.
	@PostMapping("/loginProcess")
	public String loginProcess(MemberVO memberVO) {
		MemberVO loginInfo = memberService.originLogin(memberVO);
		return "login_result";
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccess() {
		return "login_success";
	}
	
	@GetMapping("/loginFail")
	public String loginFail() {
		return "login_fail";
	}
	
	public void aaa() {
	}
	
	
	

}
