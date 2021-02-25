package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//Bean등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것. 
@Configuration // 빈등록 ( ioc관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다. = 스프링 시큐리티가 활성화된것을 그 설정을 요 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean // ioc가 된다.: 아래 함수가 리턴하는 값을 스프링이 관리한다.
	public BCryptPasswordEncoder endcodePW() { //// ##3##여기서 패스워드인코더로 사용자가 입력한 password를 다시 암호화해서 DB와 비교를 해준다. 그럼 비교해서 username도 정상이고, password도 정상인 것을 확인했으므로
		// ##4## 스프링 시큐리 영역에 우리의 user정보가 저장이 된다.
		// ##5## 그래서 로그인이 되고 나면 /로 이동(BoardController로 이동. )
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인을 해주는데, password를 가로채기를 하는데, 
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(endcodePW());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
		.csrf().disable() // csrf토큰 비활성화(테스트시 비활성화 걸어두는게 좋음)
		.authorizeRequests()
		  .antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**")
		  .permitAll()
		  .anyRequest()
		  .authenticated() // (1) 인증이 되지 않은 요청은 
		.and()
	      .formLogin()      
		  .loginPage("/auth/loginForm") // (2) 여기 로그인 폼으로 온다. 그리고 내가 로그인 폼에서 제출을 누르면 /auth/loginProc이 요청을 보내고, 이 요청을 스프링 시큐리티가 가로챈다.
		  .loginProcessingUrl("/auth/loginProc") //(3) 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 해준다.
		  .defaultSuccessUrl("/"); // (4) 정상적 로그인이라면 "/"로 이동시켜준다
	  	  //.failureUrl("실패하면 여기로");
	  //// ##1##<<< 로그인 요청이 들어오면 " /auth/loginProc " 얘를 가로채고, PrincipalDetailService여기로 던진다. -> PrincipalDetailService여기로 이동.
	  //
	}

	private Object authorizeRequests() {
		// TODO Auto-generated method stub
		return null;
	}

}
