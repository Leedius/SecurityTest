package com.study.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


//인증과 인가에 대한 security설정
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//throws Exception 예외처리
	//메소드를 사용하기 위해 @Bean을 사용
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		//csrf 해킹을 막기위한 기본 설정을 해제
		//security.csrf().disable();
		
		//인증 및 인가(권한) 설정(모든 페이지에서 인증 기능 해제)
		//security.authorizeHttpRequests().anyRequest().permitAll();
		security.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/se/main"
								, "/se/login"
								, "/se/loginProcess"
								, "login"
								, "/se/loginFail").permitAll() //이게 없으면 /se/login갈때도 인증이 안되있기때문에 무한루프가 돈다. 
				.anyRequest().authenticated()	//그 외의 요청은 다 인증받아야 합니다.
			.and()
				.formLogin()	//권한 없으면 로그인 페이지 씀.
				.loginPage("/se/login")		//로그인 페이지 경로 설정
				.usernameParameter("memId")		//id의 매개변수의 이름 지정
				.passwordParameter("memPw")		//pw의 매개변수의 이름 지정
				.loginProcessingUrl("/loginProcess")		//로그인이 처리되는 경로
				//아래의 로그인 성공시 이동 경로는 우선순위가 제일 낮다.
				//원래 이동url이 있다면 그쪽으로 이동한다.
				//무조건 기본경로 보내려면 두번째 매개변수로 true를 주면된다.
				.defaultSuccessUrl("/se/loginSuccess")	//로그인 성공시 이동 경로
				.failureUrl("/se/loginFail");		//로그인 실패시 이동 경로
		return security.build();
	}
}
