package com.example.demo.entity;
/*
 * 실제 DB와 매핑되는 클래스
 * DB Table에 존재하는 Column = 필드값 
 * JPA사용시 필요
 * */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Mysql:  DB에 위임 , AUTO 사용시 DB에 따라 JPA가 자동으로 선택함 
	private int memberNo;
	
	@Column(nullable = false)
	private String memberId;
	
	@Column(nullable = false)
	private String memberName;
	
	@Column(nullable = false)
	private String memberPw;
	
	@Column(nullable = true)
	private String memberEmail;
	
	@Column(nullable = true)
	private String memberPhone;
}
