package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username,password,email데이터를 가지고(요청)
	@PostMapping("/dummy/join")
	public String join(User user){ // key=value로 값을 받아준다. 약속이다! 
		System.out.println("userid:"+user.getId());
		System.out.println("userrole:"+user.getRole());
		System.out.println("createDate:"+user.getCreateDate());
		System.out.println("username:"+user.getUsername());
		System.out.println("userpassword:"+user.getPassword());
		System.out.println("useremail:"+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
