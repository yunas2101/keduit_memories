package com.kedu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.dto.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession mybatis;
	
	public void insert (BoardDTO dto) {
		mybatis.insert("Board.insert", dto);
	}
	
	public List<BoardDTO> selectAll(){
		return mybatis.selectList("Board.selectAll");
	}

	public BoardDTO selectOne(int seq){
		return mybatis.selectOne("Board.selectOne", seq);
	}

	public int delete(int seq){
		return mybatis.delete("Board.delete", seq);
	}

	public int modify(BoardDTO dto){
		return mybatis.update("Board.modify", dto);
	}


}
