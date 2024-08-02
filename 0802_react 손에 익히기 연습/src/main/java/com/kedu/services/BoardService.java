package com.kedu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.dao.BoardDAO;
import com.kedu.dto.BoardDTO;

@Service
public class BoardService {

	
	@Autowired
	private BoardDAO bDao;
	
	public void insert(BoardDTO dto) {
		bDao.insert(dto);
	}
	
	
	public List<BoardDTO> selectAll(){
		return bDao.selectAll();
	}
		
	public BoardDTO selectOne(int seq){
		return bDao.selectOne(seq);
	}
	
	public int delete(int seq){
		return bDao.delete(seq);
	}
	
	public int modify(BoardDTO dto){
		return bDao.modify(dto);
	}
	
	
}
