package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	
	@GetMapping({"/",""}) //// ##6## 여기서는 세션이 만들어진 상태이다.
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 1 - 1 : 근데 컨트롤러에서 세션을 어떻게 찾나?
		System.out.println("로그인 사용자 아이디: "+principal.getUsername());
		//  /WEB-INF/views/index.jsp
		return "index";
	}// (로그아웃은 스프링 시큐리티가 디폴트로 해준다.)
}
