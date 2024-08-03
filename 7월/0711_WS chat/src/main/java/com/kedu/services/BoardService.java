package com.kedu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.dao.BoardDAO;
import com.kedu.dao.FilesDAO;
import com.kedu.dao.MembersDAO;
import com.kedu.dao.ReplyDAO;
import com.kedu.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired
	private MembersDAO mdao;
	@Autowired
	private BoardDAO bdao;
	@Autowired
	private ReplyDAO rdao;
	@Autowired
	private FilesDAO fdao;
	
	

	/** 게시글 출력 **/
	@Transactional
	public List<BoardDTO> selectAll(int cpage, int record_count_per_page) throws Exception {
		return bdao.selectAll(cpage, record_count_per_page);
	}
	
	/** 게시글 상세보기 **/
	public BoardDTO selectBoard(int seq) throws Exception {
		return bdao.selectBoard(seq);
	}
	
	/** 조회수 증가 **/
	public int updateViewCount(int seq) throws Exception {
		return bdao.updateViewCount(seq);
	}
	
	/** 게시글 수정 **/
	public int updateBoard(String title, String contents, int seq) throws Exception {
		return bdao.updateBoard(title, contents, seq);
	}
	
	/** 게시글 삭제 **/
	public int deleteBoard(int seq) throws Exception {
		return bdao.deleteBoard(seq);
	}
	
	/** 검색 **/
	public List<BoardDTO> search(String writer, String title) throws Exception {
		return bdao.search(writer, title);
	}
	
	/** 게시글 총 갯수 (페이징 사용) **/
	public int getBoardCount() throws Exception {
		return bdao.getBoardCount();
	}
	
}
