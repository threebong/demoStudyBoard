package com.example.demo.web.joinMember.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.SpringSecurityConfig;
import com.example.demo.web.mapper.joinMember.JoinMemberMapper;

import lombok.Getter;
import lombok.Setter;


@Service
public class JoinMemberService {

	@Autowired
	private JoinMemberMapper joinMemberMapper;
	


	//회원가입
	public int joinMember(HashMap<String, Object> request) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String encodePw = encoder.encode((String)request.get("memberPw"));		
		request.put("memberPw", encodePw);
		/*
		 * 로그인확인시 encoder.matches(password, member.getPassword()) 사용
		 * */
		return joinMemberMapper.joinMember(request);
	}
	//중복 id검사
	public int selectOneMemberId(String memberId) throws Exception{
		return joinMemberMapper.selectOneMemberId(memberId);
	}
}
