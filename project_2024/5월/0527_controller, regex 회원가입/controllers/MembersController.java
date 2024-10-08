package controllers;

import java.io.IOException;
import java.sql.Timestamp;

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

		MembersDAO dao = MembersDAO.getInstance();
		
		String cmd = request.getRequestURI();
		System.out.println("사용자 요청 : " + cmd);
		
		try {
	
			if (cmd.equals("/idcheck.members")) {
				String id = request.getParameter("id");

				boolean result = dao.idcheck(id);
				
				request.setAttribute("result", result);
				request.getRequestDispatcher("/members/idcheck.jsp").forward(request, response);

			} else if (cmd.equals("/signup.members")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("post");
				String add1 = request.getParameter("add1");
				String add2 = request.getParameter("add2");
//				Timestamp join_date = new Timestamp(System.currentTimeMillis());
				
				
				MembersDTO dto = new MembersDTO(id,pw,name,phone,email,zipcode,add1,add2,null);
				
				dao.insert(dto);
				response.sendRedirect("/joinSuccess.jsp");
				
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
