package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	
	@GetMapping({"/",""}) //// ##6## 여기서는 세션이 만들어진 상태이다.
	public String index() { // 1 - 1 : 근데 컨트롤러에서 세션을 어떻게 찾나?

		//  /WEB-INF/views/index.jsp
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
//(로그아웃은 스프링 시큐리티가 디폴트로 해준다.)