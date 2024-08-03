package com.kedu.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kedu.dto.ChatDTO;
import com.kedu.services.ChatService;

@Controller
@RequestMapping("/chat/")
public class ChatController {

	@Autowired 
	private HttpSession session;
	@Autowired 
	private ChatService cserv;
	
	
	/** 채팅 출력 **/
	@RequestMapping("chatList")
	public String selectAll(ChatDTO dto, Model model) throws Exception {
		List<ChatDTO> chat = cserv.selectAll(dto);
		
		model.addAttribute("chat", chat);
		return "chat";
	}
	
	
	
}
