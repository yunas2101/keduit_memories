package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("update_id");
		String title = request.getParameter("update_title");
		String genre = request.getParameter("update_genre");
	
		MovieDAO dao = MovieDAO.getInstance();
		
		try {
			
			int result = dao.update(id, title, genre);
			
			if (result > 0) {
				response.sendRedirect("OutputServlet");
			} else {
				request.setAttribute("result", 3);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
		} catch (NumberFormatException e) {
			request.setAttribute("result", 3);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
