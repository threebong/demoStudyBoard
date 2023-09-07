package com.example.demo.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Member;


/*
 * Spring Security 유저 인증과정에서 UserDetails 참고하여 인증 진행 
 * */

public class CustomUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2783263553210549779L;
	private Member member;
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}
	
	
	//해당 User의 권한 return 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(() -> {return "ROLE_NORMAL";});
		return collectors;
	}

	@Override
	public String getPassword() {
		return member.getMemberPw();
	}

	@Override
	public String getUsername() {
		return member.getMemberId();
	}
	
	public Member getMember() {
		return this.member;
	}
	
	//jwt이용할것이므로 true로 변경
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;//계정 만료 안됨
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;//계정 잠기지 않음
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;//비밀번호 만료 안됨
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;//계정 활성화 됨 
	}

}
