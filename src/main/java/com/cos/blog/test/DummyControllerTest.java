package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// html파일이 아니라 data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// http://localhost:8000/blog/dummy/user/id
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			return "삭제 필패!! 해당 아이디 "+id+"는 없습니다.";
		}
		return "삭제완료!!1"+id;	
	}
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
	// email, password 받고 수정해야한다.
	@ Transactional // 얘가 있으면 아래에 주석처리한 save함수 없이 업데이트가 된다. :함수 종료 시에 자동으로 commit 해준다.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {// Json데이터를 요청 -> MessageConverter의 Jackson라이브러리가 java Object로 변환해서 받아준다.
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{// (1) JPA에서 객체를 받아온다. 해당 객체를 영속화시킨다.
			return new IllegalArgumentException("수정에 실패!!!");
		});
		user.setPassword(requestUser.getPassword()); // (2) 수정한다.
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		//더티 체킹
		return user; // (3) 여기서(@transactional에 의해) 수정된 객체를 영속성 컨텍스트의 객체와 비교하여 영속성 컨텍스트의 객체를 수정하고 그 객체를 db에 flush한다.
	}                // 이와 같은 방법을 더티 체킹이라한다.
	//http://localhost:8000/blog/dummy/user
	@GetMapping("dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//http://localhost:8000/blog/dummy/user/숫자(id)
	@GetMapping("/dummy/user/{id}")//{id}주소로 파라미터를 전달받을 수 있음.
	public User detail(@PathVariable int id){
		// 만약 user/4를 요청했을 때 데이터베이스에서 못찾으면 user가 null이된다.
		// 그럼 return null이 되는데 이는 프로그램의 문제임.
		// 그러니 Optional로 너의 User객체를 가져올테니 null인지 아닌지 판단해서 return해라.
		
		// 람다식으로도 가능하다.
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자가 없습니다.");
//		});
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {//findById는 optional을 반환. : findById(id).get()을 쓰면: 아니! 난 null반환할 일 절대 없어!라는 의미
			@Override//없는 user를 요청하면 아래 메소드를 실행 - 빈 객체를 넣어준다.
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없음");
			}
		});
		
		//요청: 웹브라우저
		// user객체는 자바 오브젝트
		// 브라우저는 자바 객체를 이해하지 못함 그래서 리턴할 때 변환을 해줘야한다. 그것이 바로 json이다.
		// 스프링부트는 MessageConvertoer라는 애가 응답시에 작동으로 작동한다.
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConvertoer가 Jackson라이브러리를 호출해서 
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
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
