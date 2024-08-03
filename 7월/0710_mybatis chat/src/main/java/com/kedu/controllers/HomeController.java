package com.kedu.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/chat")
	public String chat(Model model) {
		String loginId = (String) session.getAttribute("loginID");
		model.addAttribute("loginId", loginId);
		return "chat";
	}
	
}
