package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Bean등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것. 
@Configuration // 빈등록 ( ioc관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다. = 스프링 시큐리티가 활성화된것을 그 설정을 요 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Bean // ioc가 된다.: 아래 함수가 리턴하는 값을 스프링이 관리한다.
	public BCryptPasswordEncoder endcodePW() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
		.csrf().disable() // csrf토큰 비활성화(테스트시 걸어두는게 좋음)
		.authorizeRequests()
		  .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
		  .permitAll()
		  .anyRequest()
		  .authenticated()
		.and()
	      .formLogin()
		  .loginPage("/auth/loginForm");
	}

	private Object authorizeRequests() {
		// TODO Auto-generated method stub
		return null;
	}

}
