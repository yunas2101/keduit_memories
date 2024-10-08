package com.kedu.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kedu.dao.BoardDAO;
import com.kedu.dao.MembersDAO;
import com.kedu.dao.ReplyDAO;
import com.kedu.dto.BoardDTO;


@Controller
@RequestMapping("/board/")
public class BoardController {

	
	@Autowired
	private MembersDAO mdao;
	@Autowired
	private BoardDAO bdao;
	@Autowired
	private ReplyDAO rdao;
	@Autowired 
	private HttpSession session;
	
	
	/** 목록 출력 **/
    @RequestMapping("list")
    public String list(Model model, String cpage) throws Exception {
        
        
        if(cpage==null) cpage="1";
        
        List<BoardDTO> list = bdao.selectAll(Integer.parseInt(cpage), 10);
        model.addAttribute("list", list);
       
        model.addAttribute("cpage", cpage);
        model.addAttribute("record_total_count", bdao.getBoardCount());
        model.addAttribute("record_count_per_page", 10);
        model.addAttribute("navi_count_per_page", 5);
        
        return "board/list";
    }
	

	/** 글 작성 폼으로 이동 **/
	@RequestMapping("writeForm")
	public String writeForm() throws Exception {
		return "board/writeForm";
	}
	
	/**	글 작성 후 DB에 저장 **/
	@RequestMapping("insert")
	public String insert(String title, String contents) throws Exception {
		String loginId = (String) session.getAttribute("loginID");
		bdao.insert(title, contents, loginId);
		return "redirect:/board/list";
	}
	
	/** 게시글 상세보기 **/
	@RequestMapping("detail")
	public String detail(int seq, Model model) throws Exception {
		BoardDTO dto = bdao.selectBoard(seq);
		
		model.addAttribute("dto", dto);
		model.addAttribute("reply", rdao.selectByParentSeq(seq));
		
		bdao.updateViewCount(seq);
		
		return "board/detail";
	}
	
	/** 게시글 수정 **/
	@RequestMapping("updateBoard")
	public String updateBoard(int seq, String title, String contents) throws Exception {
		bdao.updateBoard(title, contents, seq);
		return "redirect:/board/detail?seq=" + seq;
	}
	
	/** 게시글 삭제 **/
	@RequestMapping("deleteBoard")
	public String deleteBoard(int seq) throws Exception {
		bdao.deleteBoard(seq);
		return "redirect:/board/list";
	}
	

	
	
	
	
	@ExceptionHandler(Exception.class) // 모든 종류의 예외
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
		
	}
	
	
	
	
}
