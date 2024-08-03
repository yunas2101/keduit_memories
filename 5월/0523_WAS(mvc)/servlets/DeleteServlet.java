package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MovieDAO dao = MovieDAO.getInstance();
		int id = Integer.parseInt(request.getParameter("delet_id"));

		try {
			dao.delete(id);

			String referer = request.getHeader("referer");
			if (referer != null && !referer.isEmpty()) {
				response.sendRedirect(referer);
			} else {
				// 이전 페이지의 URL이 없는 경우 특정 페이지로 이동하거나 에러 처리를 수행할 수 있습니다.
				// 예를 들어, 홈 페이지로 이동하도록 설정할 수 있습니다.
				response.sendRedirect("/home");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
