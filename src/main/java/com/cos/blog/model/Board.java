package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴! 따로 알아보기.
@Entity //User클래스가 Mysql에 테이블이 생성된다.
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //= auto_increment
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob // 대용량 데이터 쓸 때 사용.
	private String content; // 섬머노트 라이브러리 사용: <html>태그가 섞여서 디자인됨 -> 용량이 커짐.

	
	private int count; //조회수
	
	@ManyToOne(fetch=FetchType.EAGER) // Many = Board , User = One ->한명의 유저는 여러개의 게시글을 쓸 수 있다. 
	@JoinColumn(name="userID")
	private User user; //DB는 오브젝트를 저장할 수 없다. 그래서 FK사용

	@OneToMany(mappedBy="board", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE) // mappedBy가 있으면 연관관계의 주인이 아니다.(=난 FK가 아니에요, 그러니 DB에 컬럼을 만들지 마세요.)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	
	@CreationTimestamp
	private Timestamp createDate;
	

}
