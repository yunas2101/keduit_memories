package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("del_id");
		MovieDAO dao = MovieDAO.getInstance();
		
		try {
			
			int result = dao.delete(id);
			System.out.println(result);
			
			if (result > 0) {
				response.sendRedirect("OutputServlet");
			
			} else {
				// 얜 동작 안함
				request.setAttribute("result", 2);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		
		} catch (NumberFormatException e) {
			// 여기에서 동작함 
			request.setAttribute("result", 2);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
