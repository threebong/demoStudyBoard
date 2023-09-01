package com.example.demo.dto;

import com.example.demo.entity.Member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
	private int memberNo;
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberEmail;
	private String memberPhone;

	@Builder
	public MemberDto(int memberNo, String memberId, String memberName, String memberPw, String memberEmail, String memberPhone) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
	}

	
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
