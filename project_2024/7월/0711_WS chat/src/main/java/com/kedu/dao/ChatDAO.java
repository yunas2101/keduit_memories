package com.kedu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.dto.ChatDTO;

@Repository
public class ChatDAO {

	@Autowired
	private SqlSession mybatis;
	
	/**
	 * 채팅 입력
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(String sender, String message) throws Exception {
		
		Map<String, Object> params = new HashMap<>();
		params.put("sender", sender);
		params.put("message", message);
		
		return mybatis.insert("chat.insert", params);
	}
	
	
	/**
	 * 채팅 출력
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<ChatDTO> selectAll(ChatDTO dto) throws Exception {
		return mybatis.selectList("chat.selectAll", dto);
		
	}
	
	
	
	
}
