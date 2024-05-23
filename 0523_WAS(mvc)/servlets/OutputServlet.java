package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dto.MovieDTO;

/**
 * Servlet implementation class OutputServlet
 */
@WebServlet("/OutputServlet")
public class OutputServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MovieDAO dao = MovieDAO.getInstance();
		
		try {
		List<MovieDTO> list = dao.selectAll();
		
		PrintWriter pw = response.getWriter();
		pw.append("<html>");
		pw.append("<head>");
		pw.append("</head>");
		pw.append("<body>");
		pw.append("<table border=1 align='center'>");
		pw.append("<tr>");
		pw.append("<th colspan=4>Movies");
		pw.append("</tr>");
		pw.append("<tr>");
		pw.append("<th>Id");
		pw.append("<th>Title");
		pw.append("<th>Genre");
		pw.append("<th>Date");
		pw.append("</tr>");

		for (MovieDTO dto : list) {
			pw.append("<tr>");
			pw.append("<td>" + dto.getId());
			pw.append("<td>" + dto.getTitle());
			pw.append("<td>" + dto.getGenre());
			pw.append("<td>" + dto.getMovie_date());
			pw.append("</tr>");
		}
		
		// 삭제
		pw.append("<tr>");
		pw.append("<td colspan=4>");
		pw.append("<form action='/DeleteServlet'>");
		pw.append("<input type='text' placeholder='Delete ID' name='delet_id'/>");
		pw.append("<button id='delete_btn'>Delete</button>");
		pw.append("</form>");
		pw.append("</td>");
		pw.append("</tr>");
		// 수정
		pw.append("<tr>");
		pw.append("<td colspan=4>");
		pw.append("<form action='/UpdateServlet'>");
		pw.append("<input type='text' placeholder='Update ID' name='update_id'/>");
		pw.append("<br>");
		pw.append("<input type='text' placeholder='Update Title' name='update_title'/>");
		pw.append("<br>");
		pw.append("<input type='text' placeholder='Update Genre' name='update_genre'/>");
		pw.append("<br>");
		pw.append("<input type='text' placeholder='Update Movie_date' name='update_date'/>");
		pw.append("<br>");
		pw.append("<button id='update_btn'>Update</button>");
		pw.append("</form>");
		pw.append("</td>");
		pw.append("</tr>");
		pw.append("<tr>");
		pw.append("<td colspan=4  align=center>");
		pw.append("<button id='back_btn'>Back</button>");
		pw.append("</td>");
		pw.append("</tr>");
		pw.append("</table>");
		
		pw.append("<script>");
		pw.append("document.getElementById('back_btn').onclick=function(){location.href='/index.html';}");
		pw.append("</script>");
		
		pw.append("</body>");
		pw.append("</html>");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
