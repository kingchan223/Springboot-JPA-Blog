package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴! 따로 알아보기.
//ORM -> java(다른 언어들 포함)Object를 테이블로 매핑해주는 기술
@Entity //User클래스가 Mysql에 테이블이 생성된다.
//@DynamicInsert : insert시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id // PK가 된다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. (mysql은 auto방식)
	private int id; //시퀸스, auto_increment
	
	@Column(nullable=false, length=30, unique=true) // nullable-not null, length-길이제한
	private String username; //아이디
	
	@Column(nullable=false, length=100) // 해쉬로 암호화된 비번을 저장해야해서 비번 길이제한을 길게 잡은 것.
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	//@ColumnDefault("user")
	// DB는 PoleType이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;//나중에는 String대신 Enum을 쓰는게 좋다. //role: ADMIN, USER
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;

}
