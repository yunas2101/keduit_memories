package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dto.MovieDTO;

/**
 * Servlet implementation class InputServlet
 */
@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		System.out.println(title + " , " + genre);
		
		MovieDTO dto = new MovieDTO(0, title, genre, null);
		MovieDAO dao = MovieDAO.getInstance();
		
		try {
			int result = dao.insert(dto);
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		PrintWriter pw = response.getWriter();
		pw.append("<html>");
		pw.append("<head>");
		pw.append("</head>");
		pw.append("<body>");
		pw.append("Success!");
		pw.append("<button id='ok'>OK</button>");
		pw.append("<script>");
		pw.append("document.getElementById('ok').onclick=function(){location.href='index.html';}");
		pw.append("</script>");
		pw.append("</body>");
		pw.append("</html>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
