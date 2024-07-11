package com.kedu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.dao.ChatDAO;
import com.kedu.dto.ChatDTO;

@Service
public class ChatService {

	@Autowired
	private ChatDAO cdao;
	
	public void func() {
		System.out.println("ChatService 동작 테스트");
	}
	
	// 채팅 입력 
	public int insert(String sender, String message) throws Exception {
		return cdao.insert(sender, message);
	}
	
	// 채팅 출력
	public List<ChatDTO> selectAll(ChatDTO dto) throws Exception {
		return cdao.selectAll(dto);
	}
	
	
	
	
	
}
