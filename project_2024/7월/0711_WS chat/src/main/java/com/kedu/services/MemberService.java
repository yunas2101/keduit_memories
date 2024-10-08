package com.kedu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.dao.MembersDAO;
import com.kedu.dto.MembersDTO;
import com.kedu.dto.ReplyDTO;

@Service
public class MemberService {

	@Autowired
	private MembersDAO dao;
	@Autowired
	private MemberService serv;
	
	/** Id 있는지 확인 **/
	public boolean isIdExist(String id) throws Exception {
		return dao.isIdExist(id);
	}
	
	/** 회원가입 **/
	public int insert(MembersDTO dto) throws Exception {
		return dao.insert(dto);
	}

	/** 로그인 **/
	public boolean isMember(MembersDTO dto) throws Exception {
		return dao.isMember(dto);
	}
	
	/** 회원탈퇴 **/
	public int deleteMember(String loginID) throws Exception {
		return dao.deleteMember(loginID);
	}
	
	
	/** 마이페이지 정보 조회 **/
	public MembersDTO selectMember(String loginID) throws Exception {
		return dao.selectMember(loginID);
	}
	
	/** 마이페이지 정보 수정 **/
	public int updateMember(String name, String id) throws Exception {
		return dao.updateMember(name, id);
	}
	
	
	
	
}
