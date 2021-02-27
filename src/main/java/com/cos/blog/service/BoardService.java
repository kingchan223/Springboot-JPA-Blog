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
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

@Service// 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해준다.=ioc를 해준다.  
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;

	
	@Transactional// 요 아래가 하나의 서비스로 묶이게 된다.
	public void 글쓰기(Board board, User user) { // title, content 받는다.
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
				return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	@Transactional
	public void 글수정하기(int id, Board requestBoard){
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
				   return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
					});// 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수 종료시에(서비스가 종료될 떄 ) 트랜잭션이 종료된다. 이때 더티 체킹이 일어나면서, 자동으로 DB에 업데이트가 flush된다.
	}
	
	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			   return new IllegalArgumentException("댓글쓰기 실패: 게시글 아이디를 찾을 수 없습니다. =");
				});// 영속화 완료

		requestReply.setUser(user);
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
	}
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
	
//	@Transactional(readOnly = true)//Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성을 유지시킨다)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
//요 아래가 하나의 서비스로 묶이게 된다.