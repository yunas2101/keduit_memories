package com.kedu.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedu.dao.BoardDAO;
import com.kedu.dao.ReplyDAO;
import com.kedu.dto.ReplyDTO;

@Controller
@RequestMapping("/reply/")
public class ReplyController {

	@Autowired
	private BoardDAO bdao;
	@Autowired
	private ReplyDAO rdao;
	@Autowired 
	private HttpSession session;
	
	/** 댓글 입력 **/
	@RequestMapping("insert")
    public String insert(ReplyDTO dto) throws Exception {
        
		String loginId = (String) session.getAttribute("loginID");
		dto.setWriter(loginId);
		
        rdao.insert(dto);
        return "redirect:/board/detail?seq="+dto.getParent_seq();
    }
	
	/** 댓글 삭제 **/
	@ResponseBody
	@RequestMapping("delete")
	public String delete(int seq) throws Exception {
		int result = rdao.delete(seq);
		return String.valueOf(result);
	}
	
	/** 취소 버튼 누르면 원래 글로 돌아가게 **/
	@ResponseBody
	@RequestMapping(value="cancel",produces = "text/html; charset=UTF8")
	public String cancel(int seq) throws Exception {
		String result = rdao.cancel(seq);
		return result;
	}
	
	/** 댓글 수정 **/
	@ResponseBody
	@RequestMapping("update")
	public String update(int seq, String contents) throws Exception {
		int result = rdao.update(seq, contents);
		return String.valueOf(result);
	}
	

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";	
	}
	 
}
