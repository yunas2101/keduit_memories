package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MembersDAO;
import dto.MembersDTO;

@WebServlet("*.mypage")
public class MypageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		MembersDAO dao = MembersDAO.getInstance();

		String cmd = request.getRequestURI();
		System.out.println("사용자 요청 : " + cmd);

		try {

			/* 내 정보 */
			if (cmd.equals("/select.mypage")) {

//				String loginID = request.getParameter("id");
//				System.out.println("loginID : " + loginID);
//				
				String loginID = "qwerqwer";
				MembersDTO dto = dao.selectMember(loginID);
				System.out.println("MembersDTO: " + dto);
				
				request.setAttribute("member", dto);
				request.getRequestDispatcher("/mypage/infoUpdate.jsp").forward(request, response);

				
				/* 내 정보 수정 */
			} else if (cmd.equals("/update.mypage")) {

				String id = (String) request.getSession().getAttribute("loginID");
				String name = request.getParameter("name");
				String nickname = request.getParameter("nickname");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String avatar = request.getParameter("avatar");

				MembersDTO dto = new MembersDTO(id, null, name, nickname, phone, email, null, null, 0, avatar, null);

				dao.edit(dto);

				response.sendRedirect("/mypage.members");

			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
