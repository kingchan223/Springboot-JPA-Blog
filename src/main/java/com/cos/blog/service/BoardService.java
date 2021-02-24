package com.cos.blog.service;


import java.util.List;

// service가 필요한 이유: 1.트랜잭션관리 2.서비스 의미 때문
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;

@Service// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다.=ioc를 해준다.  
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	
	@Transactional// 요 아래가 하나의 서비스로 묶이게 된다.
	public void 글쓰기(Board board, User user) { // title, content 받는다.
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
		
	}
	
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}

//	@Transactional(readOnly = true)//Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성을 유지시킨다)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
//요 아래가 하나의 서비스로 묶이게 된다.