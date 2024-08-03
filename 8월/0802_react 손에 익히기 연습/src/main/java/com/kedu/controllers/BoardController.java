package com.kedu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.dto.BoardDTO;
import com.kedu.services.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	
	@Autowired
	private BoardService bServ;
	
	
	/**
	 * C - insert 
	 * R - list, detail
	 * U - update
	 * D - delete
	 */
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody BoardDTO dto) {
		String member_id = "member_id";
		dto.setMember_id(member_id);
		
		bServ.insert(dto);
		return ResponseEntity.ok().build();
	}

	
	@GetMapping
	public ResponseEntity<List<BoardDTO>> selectAll (){
		
		List<BoardDTO> list = bServ.selectAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{seq}")
	public ResponseEntity<BoardDTO> selectOne (@PathVariable int seq){
		
		BoardDTO result = bServ.selectOne(seq);
		return ResponseEntity.ok(result);
	}
	
	
	@DeleteMapping("/{seq}")
	public ResponseEntity<Integer> delete(@PathVariable int seq){
		
		int result = bServ.delete(seq);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping
	public ResponseEntity<Integer> modify(@RequestBody BoardDTO dto){
		
		int result = bServ.modify(dto);
		return ResponseEntity.ok(result);
	}
	
	
	
	
	
	
	
	
	
}
