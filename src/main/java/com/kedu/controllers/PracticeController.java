package com.kedu.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice")
public class PracticeController {
	
	@PostMapping
	public ResponseEntity<Void> practice(){	
		System.out.println("안뇽");
		System.out.println("이 바보들 !");
		System.out.println("나눈 유나");
		String yuna = "hi";
		System.out.println("240902 크라켄 연습!");
		return ResponseEntity.ok().build();
	}
}
