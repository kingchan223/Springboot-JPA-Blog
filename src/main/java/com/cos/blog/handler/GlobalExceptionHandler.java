package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice // 어떤 Exception이 발생해도 여기로 들어오게 해준다.
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)// IllegalArgumentException 요 에러가 뜨면 이 함수에서 처리해준다. Exception를 적으면 ㅁㅎ든 예외처리헤준다.
	public String handleArgumentException(Exception e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
	
}
