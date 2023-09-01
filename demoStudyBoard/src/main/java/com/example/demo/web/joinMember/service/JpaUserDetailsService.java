package com.example.demo.web.joinMember.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.auth.CustomUserDetails;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
/*
 * Spring Security에서
 * UserDetails 토대로 정보 불러올때 사용
 * 
 * */
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("::::USER::::");
		
		Member member =memberRepository.findByMemberId(username);
//				.orElseThrow(
//					() -> new UsernameNotFoundException(":::::::::Invalid authentication::::::::::::")
//				)
//				;
		System.out.println(member.getMemberNo());
		
		
		return new CustomUserDetails(member);
	}

}
