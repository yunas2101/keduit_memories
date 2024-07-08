package com.kedu.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedu.dao.MembersDAO;
import com.kedu.dto.MembersDTO;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired // 자동으로 연결해라. 
	private MembersDAO dao;

	@Autowired 
	private HttpSession session;

	/** join.jsp로 이동 **/
	@RequestMapping("join")
	public String join() throws Exception {
		return "member/join";
	}

	/** Id 있는지 확인 **/
	@ResponseBody // return에 의해 반환되는 값은 forward도 아니고 redirect도 아님. 디스패쳐야. 너는 result값을 받으면 있는 그대로를 돌려보내
	@RequestMapping(value="idcheck", produces="text/html;charset=utf8")
	public String idcheck(String id) throws Exception {
		boolean result = dao.isIdExist(id);
		System.out.println(result);
		return String.valueOf(result); // true / false 라는 문자열로 변환하여 보낼 것
	}
	
	/** 회원가입 후 **/
	@RequestMapping("joinProc")
	public String joinProc(MembersDTO dto) throws Exception {
		dao.insert(dto);
		return "home";
	}
	
	/** 로그인 **/
	@RequestMapping("login")
	public String login(MembersDTO dto) throws Exception {
		boolean result = dao.isMember(dto);
		if(result) {
			session.setAttribute("loginID", dto.getId());
		} 
		return "home";
	}

	/** 로그아웃 **/
	@RequestMapping("logout")
	public String logout() throws Exception {
		session.invalidate();		
		return "redirect:/";
	}

	
	/** 회원탈퇴 **/
	@RequestMapping("signout")
	public String signout() throws Exception {
		String loginId = (String) session.getAttribute("loginID");
		int result = dao.deleteMember(loginId);
		return "redirect:/member/logout";
	}
	
	
	/** 마이페이지 정보 조회 **/
	@RequestMapping("mypage")
	public String mypage(Model model) throws Exception {
		
		String loginID = (String)session.getAttribute("loginID");
		MembersDTO dto = dao.selectMember(loginID);
		
		model.addAttribute("dto", dto);

		return "member/mypage";
	}

	/** 마이페이지 정보 수정 **/
	@RequestMapping("update")
	public String mypage(String name) throws Exception {
		System.out.println(name);
		String loginID = (String)session.getAttribute("loginID");
		dao.updateMember(name, loginID);
		
		
		return "redirect:/member/mypage";
	}
	
	
	
	
	@ExceptionHandler(Exception.class) // 모든 종류의 예외
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
		
	}
	

}
