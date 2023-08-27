package com.example.demo.web.joinMember.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.web.mapper.joinMember.JoinMemberMapper;

import lombok.Getter;
import lombok.Setter;


@Service
public class JoinMemberService {

	@Autowired
	private JoinMemberMapper joinMemberMapper;
	


	
	public int joinMember(HashMap<String, Object> request) throws Exception {
		return joinMemberMapper.joinMember(request);
	}
}
