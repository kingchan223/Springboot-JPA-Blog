package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/",""}) //// ##6## 여기서는 세션이 만들어진 상태이다.
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) { // 1 - 1 : 근데 컨트롤러에서 세션을 어떻게 찾나?
		model.addAttribute("boards",boardService.글목록(pageable));
		return "index"; //@Controller에 의해 viewResolver가 작동. -> 해당 "index"로 윗줄의 model의 정보를 날린다.
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		//model.
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model){
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
		
		
	}
	
	// USER권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
//(로그아웃은 스프링 시큐리티가 디폴트로 해준다.)