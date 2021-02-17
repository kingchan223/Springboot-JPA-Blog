package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 연결 전력을 따른다.
	private int id; // 시퀸스, auto_increment
	
	@Column(nullable = false, length=200)
	private String content;
	
	@ManyToOne // 여러 개의 댓글이 하나의 게시글에 가능하다.
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 여러 개의 댓글이 하나의 유저가 달 수 있다.
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
