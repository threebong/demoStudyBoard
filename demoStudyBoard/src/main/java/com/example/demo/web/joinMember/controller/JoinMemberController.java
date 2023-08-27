package com.example.demo.web.joinMember.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.web.joinMember.service.JoinMemberService;

@RestController
@RequestMapping("/api")
public class JoinMemberController {
	
	@Autowired
	private JoinMemberService joinMemberService;
	
	/*
	 * 회원가입 
	 * */
	@PostMapping("/joinMember")
	public ArrayList<HashMap<String, Object>> joinMember(@RequestBody HashMap<String, Object> request) throws Exception{
		ArrayList<HashMap<String, Object>> rtnArray = new ArrayList<HashMap<String, Object>>();
		HashMap<String,Object> rtnMap = new HashMap<String,Object>();
		System.out.println("----->");
		System.out.println(request);
		rtnMap.put("", rtnMap.get("memberId"));
		rtnArray.add(rtnMap);
		
		try {
			joinMemberService.joinMember(request);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return rtnArray;
	}
}
