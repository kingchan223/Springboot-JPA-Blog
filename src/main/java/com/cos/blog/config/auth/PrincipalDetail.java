package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료하면 UserDetails타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장한다. 그때 저장되는게 PrincipalDetail이다.
@Getter 
public class PrincipalDetail implements UserDetails{ // PrincipalDetail가 User객체를 품고있다. 이런걸 콤포지션이라고 한다. 
	private User user;   //User는 model.User가 들고있는 User

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. (true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 안잠겨있는지를 리턴. (ture: 안잠겨짐)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되었는지를 알려준다.  (true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	//  계정 활상화(사용 가능)인지 리턴한다. (true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러 개 있을 수 있어서 루프를 돌아야 하는데 우리는 한 개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole(); // ROLE_USER 이런 식으로 리턴된다. ,  ROLE_ 이거 꼭 붙여줘야 한다.
//			}
//		});
		
		collectors.add(()->{return "ROLE_"+user.getRole();});
		return collectors;
	}

}
