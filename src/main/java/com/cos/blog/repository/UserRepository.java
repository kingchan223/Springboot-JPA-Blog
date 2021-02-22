package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;


// DAO의 역할.
// 자동으로 bean등록이 된다.
// @Repository를 생략가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {//해당 레파지토리는 User테이블을 관리하는 애, 그리고 User테이블의 PK는 int형이야. 
//JpaRepository는 findAll을 들고있다.

}
//JPA Naming 쿼리
	// SELECT * FROM user WHERE username = ?(param1) AND password = ?(param2);
	//User findByUsernameAndPassword(String username, String password);
	
	//@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	//User login(String username, String password);