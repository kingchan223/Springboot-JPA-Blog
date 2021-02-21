package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //얘가 붙면 기본적으로 해당경로 이하 파일을 리턴함. @RestController는 스트링을 리턴하면 그 문자 자체를 리턴한다.
public class TempControllerTest {
	
	//http://localhost:8000/blog/temp/home -> 8000/blog는 yml에서 설정한것!
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("/temp/home()!!!");
		//파일리턴 기본경로: src/main/resources/static
		//리턴명: /home.html
		//풀경로: src/main/resources/static/home.html
		return "home";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix: /WEB-INF/views/ : prefix:맨 처음붙는애  -->yml의 spring에서 지정한것. 
		//suffix: .jsp            : suffix:맨 마지막에 붙는애
		// 풀경로: /WEB-INF/views/test.jsp
		return "test";
	}
}	
