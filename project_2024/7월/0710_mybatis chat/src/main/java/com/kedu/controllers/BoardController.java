package com.kedu.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dao.BoardDAO;
import com.kedu.dao.FilesDAO;
import com.kedu.dao.MembersDAO;
import com.kedu.dao.ReplyDAO;
import com.kedu.dto.BoardDTO;
import com.kedu.services.BoardService;
import com.kedu.services.FilesService;
import com.kedu.services.ReplyService;


@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired 
	private HttpSession session;
	@Autowired
	private FilesService fserv;
	@Autowired
	private BoardService bserv;
	@Autowired
	private ReplyService rserv;
	
// ===================================================
	
	/** 글 작성 폼으로 이동 **/
	@RequestMapping("writeForm")
	public String writeForm() throws Exception {
		return "board/writeForm";
	}
	
	
	/**	글 작성 후 DB에 저장 **/
	@RequestMapping("insert")
	public String insert(BoardDTO dto, MultipartFile[] files) throws Exception {
		String login_id = (String) session.getAttribute("loginID");
		String realPath = session.getServletContext().getRealPath("upload");

		
		if(login_id != null) {
			dto.setWriter(login_id); // DTO의 Writer를 login_id로 설정
			
			fserv.upload(dto, realPath, files); 
			
			return "redirect:/board/list"; // 게시글 목록 페이지로 리다이렉트

		} else {
			return "redirect:/"; // 로그인되지 않은 경우 처리
		}
	}
	
	
	/** 목록 출력 **/
    @RequestMapping("list")
    public String list(Model model, String cpage) throws Exception {
        
        if(cpage==null) cpage="1";
        
//        List<BoardDTO> list = bdao.selectAll(Integer.parseInt(cpage), 10);
        List<BoardDTO> list = bserv.selectAll(Integer.parseInt(cpage), 10);
        model.addAttribute("list", list);
       
        model.addAttribute("cpage", cpage);
//        model.addAttribute("record_total_count", bdao.getBoardCount());
        model.addAttribute("record_total_count", bserv.getBoardCount());
        model.addAttribute("record_count_per_page", 10);
        model.addAttribute("navi_count_per_page", 5);
        
        return "board/list";
    }
	

	/** 게시글 상세보기 **/
	@RequestMapping("detail")
	public String detail(int seq, Model model) throws Exception {
		BoardDTO dto = bserv.selectBoard(seq);
		
		model.addAttribute("dto", dto);
		model.addAttribute("reply", rserv.selectByParentSeq(seq));
		
		bserv.updateViewCount(seq);
		
		return "board/detail";
	}
	
	
	/** 게시글 수정 **/
	@RequestMapping("updateBoard")
	public String updateBoard(int seq, String title, String contents) throws Exception {
		bserv.updateBoard(title, contents, seq);
		return "redirect:/board/detail?seq=" + seq;
	}
	
	/** 게시글 삭제 **/
	@RequestMapping("deleteBoard")
	public String deleteBoard(int seq) throws Exception {
		bserv.deleteBoard(seq);
		return "redirect:/board/list";
	}
	
	/** 검색 **/
	@RequestMapping("search")
	public String search(String writer, String title) throws Exception {
		bserv.search(writer, title);
		return "redirect:/board/list";
	}
	

	@ExceptionHandler(Exception.class) // 모든 종류의 예외
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
		
	}
	
	
	
	
}
