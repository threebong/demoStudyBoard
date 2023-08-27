package com.example.demo.web.mapper.joinMember;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface  JoinMemberMapper {
	
	public int joinMember( HashMap<String, Object> request) throws Exception; //회원가입
}
