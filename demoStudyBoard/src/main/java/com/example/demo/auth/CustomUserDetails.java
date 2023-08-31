package com.example.demo.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return member.getMemberPw();
	}

	@Override
	public String getUsername() {
		return member.getMemberId();
	}

	
	//jwt이용할것이므로 true로 변경
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
