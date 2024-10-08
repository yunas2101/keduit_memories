package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MembersDAO;
import dto.MembersDTO;

@WebServlet("*.members")
public class MembersController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); 
		MembersDAO dao = MembersDAO.getInstance();
		
		String cmd = request.getRequestURI();
		System.out.println("사용자 요청 : " + cmd);

		try {
			
			// 회원가입
			if(cmd.equals("/signup.members")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String post = request.getParameter("post");
				String add1 = request.getParameter("add1");
				String add2 = request.getParameter("add2");
				
				MembersDTO dto = new MembersDTO(id,pw,name,phone,email,post,add1,add2,null);
				
				dao.insert(dto);
				response.sendRedirect("/index.jsp");
				
				
				// id 중복체크
			} else if(cmd.equals("/idcheck.members")) {
				String id = request.getParameter("id");
				
				boolean result = dao.idcheck(id);
				
				request.setAttribute("result", result);
				request.getRequestDispatcher("/members/idcheck.jsp").forward(request, response);
				
				/* 로그인 */
			} else if (cmd.equals("/login.members")) {
				
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				
				boolean result = dao.login(id, pw);
				if(result) {
					System.out.println("로그인 성공");
					HttpSession session = request.getSession();
					session.setAttribute("loginID", id);
				} else {
					System.out.println("실패");
				}
				
				response.sendRedirect("/index.jsp");
			
				/* 로그아웃 */
			} else if (cmd.equals("/logout.members")) {
				request.getSession().invalidate();
				response.sendRedirect("/index.jsp");
			
				/* 회원탈퇴 */
			} else if (cmd.equals("/memberout.members")) {
				
				HttpSession session = request.getSession();
				String loginID = (String) session.getAttribute("loginID");
			
				if(loginID != null) { // 로그인 된 사용자가 있을 경우
					int result = dao.delete(loginID);
					
					if(result > 0) {
						session.invalidate();
						response.sendRedirect("/index.jsp");
					} else {
						response.sendRedirect("/error.jsp");
					}
				} else {
					response.sendRedirect("/login.jsp");
				}
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
