package com.cos.blog.service;
import javax.transaction.Transactional;

// service가 필요한 이유: 1.트랜잭션관리 2.서비스 의미 때문
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다.=ioc를 해준다.  
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional// 요 아래가 하나의 서비스로 묶이게 된다.
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService: 회원가입():"+ e.getMessage());
		}
		return -1;
	}
}
