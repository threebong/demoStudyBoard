package com.example.demo.dto;

import com.example.demo.entity.Member;


public class MemberDto {
	private int memberNo;
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberEmail;
	private String memberPhone;

	
	/*
	 *  DTO --> Entity 
	 * */
	public Member toEntity() {
		Member member = Member.builder()
								.memberNo(this.memberNo)
								.memberId(this.memberId)
								.memberName(this.memberName)
								.memberPw(this.memberPw)
								.memberEmail(this.memberEmail)
								.memberPhone(this.memberPhone)
								.build();
		return member;
	}
}
