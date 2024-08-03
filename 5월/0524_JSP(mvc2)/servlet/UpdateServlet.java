package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dto.MovieDTO;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MovieDAO dao = MovieDAO.getInstance();
		String update_id = request.getParameter("update_id");
		String update_title = request.getParameter("update_title");
		String update_genre = request.getParameter("update_genre");
//		String update_date = request.getParameter("update_date");

		MovieDTO dto = new MovieDTO(Integer.parseInt(update_id), update_title, update_genre, null);

		try {
			int result = dao.update(dto);
//	        request.setAttribute("result", result); // jsp에 result 값 넣어주기
//			request.getRequestDispatcher("OutputServlet").forward(request, response);
			
			if (result > 0) {
				response.sendRedirect("OutputServlet");
			} else {
	        request.setAttribute("result", 3); // jsp에 result 값 넣어주기
			request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			

		} catch (Exception e) {
			
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
