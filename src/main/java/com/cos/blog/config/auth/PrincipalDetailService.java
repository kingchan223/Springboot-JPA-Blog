package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // Bean등
public class PrincipalDetailService implements  UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌때, username, password 변수 2개를 가로채는데, 
	// password 부분 처리는 알아서 함.
	// username이 DB에 있는지만 확인해주면 된다. 이 확인을 아래 함수에서 한다. 
	@Override  ////// <<< ##2## 여기서 1번에서 던진 정보를 username에 유저네임이 들어가고, 비교하고, PrincipalDetail(principal) 얘를리턴해주는데 ㄹㅣ턴할 때, SecurityConfig로 이동.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."+username);
				});
		return new PrincipalDetail(principal); // 시큐리티 세션에 유저정보가 저장이된다. 아이디:user, 패스워드:콘솔창 긴거
	}	
}
