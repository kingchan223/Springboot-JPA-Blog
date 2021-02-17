package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


// DAO의 역할.
// 자동으로 bean등록이 된다.
// @Repository를 생략가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {//해당 레파지토리는 Use테이블을 관리하는 애, 그리고 User테이블의 PK는 int형이야. 
//JpaRepository는 findAll을 들고있다.
}
