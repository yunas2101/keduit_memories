package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import commons.EncryptionUtils;
import dao.MembersDAO;
import dto.MembersDTO;



@WebServlet("*.members")
public class MembersController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8"); 
		// 클라이언트로부터 전송되는 문자열에 대한 인코딩을 utf8 처리
		// request에서 값을 꺼내기 전에 처리해야만 함!
		
		MembersDAO dao = MembersDAO.getInstance();
		
		String cmd = request.getRequestURI();
		System.out.println("사용자 요청 : " + cmd);
		
		try {
	
			/* ID 중복체크 */
			if (cmd.equals("/idcheck.members")) {
				String id = request.getParameter("id");

				boolean result = dao.idcheck(id);
				
				request.setAttribute("result", result);
				request.getRequestDispatcher("/members/idcheck.jsp").forward(request, response);

				/* 회원가입 */
			} else if (cmd.equals("/signup.members")) {
				String id = request.getParameter("id");
				String pw = EncryptionUtils.getSHA512(request.getParameter("pw"));
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("post");
				String add1 = request.getParameter("add1");
				String add2 = request.getParameter("add2");
//				Timestamp join_date = new Timestamp(System.currentTimeMillis());
				
				MembersDTO dto = new MembersDTO(id,pw,name,phone,email,zipcode,add1,add2,null);
				
				dao.insert(dto);
				response.sendRedirect("/index.jsp");
				
				/* 로그인 */
			} else if (cmd.equals("/login.members")) {
				
				String id = request.getParameter("id");
				String pw = EncryptionUtils.getSHA512(request.getParameter("pw"));
				
				boolean result = dao.login(id, pw);
				if(result) {
				
					HttpSession session =  request.getSession(); // session 사용
					session.setAttribute("loginID", id);
//					session.setAttribute("loginWriter", writer);
					
				}
				
				response.sendRedirect("/index.jsp");
				
				/* 로그아웃 */
			} else if (cmd.equals("/logout.members")) {
				HttpSession session =  request.getSession();
				session.invalidate(); // session 가지고 있던 거 다 날라감
				response.sendRedirect("/index.jsp");
				
				/* 회원탈퇴 */
			} else if (cmd.equals("/memberout.members")) {
				
				 HttpSession session = request.getSession();
				 String loginID = (String) request.getSession().getAttribute("loginID");
				
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
			
				 /* 내 정보 */
			} else if (cmd.equals("/mypage.members")) {
				
				HttpSession session =  request.getSession();
				String loginID = (String) session.getAttribute("loginID");
				
				MembersDTO dto = dao.selectMember(loginID); 
				request.setAttribute("member", dto);
				request.getRequestDispatcher("/members/mypage.jsp").forward(request, response);
			
				
				/* 수정한 정보 */
			} else if (cmd.equals("/edit.members")) {
				
				String id = (String) request.getSession().getAttribute("loginID");
//				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("post");
				String add1 = request.getParameter("add1");
				String add2 = request.getParameter("add2");
				
				MembersDTO dto = new MembersDTO(id, null, name, phone, email, zipcode, add1, add2, null);
				
				dao.edit(dto);
				
//				response.sendRedirect("/index.jsp");
				response.sendRedirect("/mypage.members");
				
			}
		
		

			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
