package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MovieDAO dao = MovieDAO.getInstance();
		String id = request.getParameter("id");
		System.out.println(id);
		
		try {

			int result = dao.delete(id);
			if(result > 0) {
				response.sendRedirect("OutputServlet");
				
			}else {
				
				request.setAttribute("result", 2);
				request.getRequestDispatcher("error.jsp").forward(request, response);
				
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
