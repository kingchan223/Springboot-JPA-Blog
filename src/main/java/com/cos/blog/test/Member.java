package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
// @Data = @Getter + @Setter
//@AllArgsConstructor // 생성자
//@RequiredArgsConstructor //  final 붙은 애들에 대한 컨스트럭터 만들어준다.

//@AllArgsConstructor//전체 생성자
@Data
@NoArgsConstructor//빈 생성자
public class Member {
	private int id;// 날아오는 데이터가 변경되지 않게 final로 잡아줌.
	private String username;
	private String password;
	private String email;
	
	//@AllArgsConstructor // 아래코드는 이거 붙인거랑 똑같다.
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
