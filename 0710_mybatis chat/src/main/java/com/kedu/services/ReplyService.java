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
import com.kedu.dto.ReplyDTO;

@Service
public class ReplyService {

	@Autowired
	private MembersDAO mdao;
	@Autowired
	private BoardDAO bdao;
	@Autowired
	private ReplyDAO rdao;
	@Autowired
	private FilesDAO fdao;
	
	
	/** 댓글 입력 **/
	public int insert(ReplyDTO dto) throws Exception {
		return rdao.insert(dto);
	}
	
	/** 댓글 출력 **/
	public List<ReplyDTO> selectByParentSeq(int seq) throws Exception {
		return rdao.selectByParentSeq(seq);
	}
	
	/** 댓글 삭제 **/
	public int delete(int seq) throws Exception {
		return rdao.delete(seq);
	}
	
	/** 댓글 취소 **/
	public String cancel(int seq) throws Exception {
		return rdao.cancel(seq);
	}
	
	/** 댓글 수정 **/
	public int update(int seq, String contents) throws Exception {
		return rdao.update(seq, contents);
	}
	
	
	
	
}
