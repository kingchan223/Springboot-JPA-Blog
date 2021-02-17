package com.cos.blog.test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

//사용자가 요청 -> 응답(html로)하려면 @Controller
//사용자가 요청 -> 응답(Data로 응답)하려면 @RestController
@RestController
public class HttpControllerTest {
	private static final String TAG="HttpControllerTest:";
	// application.yml에서 server/port를 설정해주었기 때문에 url이 바뀐다.
	@GetMapping("/http/lombok") //->localhost:8000/blog/http/lombok
	public String lombokTest() {
		Member m = Member.builder().username("lee").password("123123123").email("314").build();//빌드패턴(@bulid)을 쓰면 오른쪽 문장을 왼쪽같이 가능.new Member(1, "lele", "123123", "123@123");
		//1. build패턴의 장점은전달할 때 순서를 뒤집어도 상광이 없다.
		//2 . 생성자는 Member m1 = new Member(id, username, password, email)처럼 이렇게 순서를 기억해서 넣워주어야하는데, 빌더는 그럴 필요가 없다.
		
		System.out.println(TAG+"getter:"+m.getUsername());
		m.setUsername("이찬영");
		System.out.println(TAG+"setter:"+m.getUsername());
		return "Lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 무조건 get만 할 수 있다.
	/// http://localhost:8080/http/get(select)
	@GetMapping("/http/get") 
	public String getTest(Member m) { // ?id=1&username=lee&password=1234&email=123@123 이 쿼리스트링을 물음표 뒤에 넣어주는 역할을 스프링이 해준다~ MessageConverter(스프링부트) 
		return "get요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	// http://localhost:8080/http/post(insert)
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)가 json으로 오는지 text로 오는지 보고 Member m변수에 매핑시켜준다.
		return "post요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	// http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청";
	}
	// http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
}
