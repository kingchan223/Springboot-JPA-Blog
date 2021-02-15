package com.cos.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

//스프링이com.cos.blog패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것은 아니고.
//특정 어노테이션이 붙어있는 클래스 파일들을 new해서(ioc) 스프링 컨테이너에 관리해준다.
@RestController
public class BlogControllerTest {

    //http://localhost:8080/test/hello
    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>Hello Spring Boot</h1>";
    }
}
