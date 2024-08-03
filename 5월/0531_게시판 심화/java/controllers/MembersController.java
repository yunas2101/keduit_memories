package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;
import dto.MembersDTO;

@WebServlet("*.members")
public class MembersController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("utf-8");
	
	MembersDAO dao = MembersDAO.getInstance();
	
	String cmd = request.getRequestURI();
	System.out.println("사용자 요청 : " + cmd);
	
	
	try{
		/* id 중복체크 */
		if(cmd.equals("/idcheck.members")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			boolean idcheck = dao.idcheck(id);
			response.sendRedirect("/members/idcheck.jsp");
			
			/* 회원가입 */
		} else if (cmd.equals("/signup.members")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String zipcode = request.getParameter("zipcode");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			
			MembersDTO dto = new MembersDTO(id,pw,name,phone,email,zipcode,address1,address2,null);
			
			dao.insert(dto);
			response.sendRedirect("/index.jsp");
			
			/*  */
		} 
//		else if (cmd.equals("")) {
//			
//			
//		}
		
		
		
		
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
		response.sendRedirect("/error.jsp");
		
	}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
