package com.cos.blog.service;


// service가 필요한 이유: 1.트랜잭션관리 2.서비스 의미 때문
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다.=ioc를 해준다.  
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional// 요 아래가 하나의 서비스로 묶이게 된다.
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();  // 원래 패스워드를 해쉬화
		String encPassword = encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword); // 해쉬된덧을 세터
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 JPA영속성 컨텍스트에 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정하면 된다. 
		// select를 해서 Userd오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서이다.
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 업데이트문을 날려준다.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패!");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		

		// 회원수정 함수 종료 = 서비스 종료 = 트랜잭션이 종료 => 커밋이 자동으로 된다.
		// 영속화된 persistance객체의 변화가 감지되면 더티체킹이 되어 변화된 것들을 update문을 자동으로 날려준다.
	}
//	@Transactional(readOnly = true)//Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성을 유지시킨다)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
//요 아래가 하나의 서비스로 묶이게 된다.