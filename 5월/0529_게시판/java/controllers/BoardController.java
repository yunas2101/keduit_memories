package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dto.BoardDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		BoardDAO dao = BoardDAO.getInstance();

		String cmd = request.getRequestURI();
		System.out.println("사용자 요청 : " + cmd);

		try {

			/* 리스트 목록 */
			if (cmd.equals("/list.board")) {

				List<BoardDTO> list = dao.selectAll();
				
				request.setAttribute("list", list);
				request.getRequestDispatcher("/board/list.jsp").forward(request, response);

				
				/* 글 작성 */
			} else if (cmd.equals("/write.board")) {

				String writer = (String) request.getSession().getAttribute("loginID");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				System.out.println(contents);
				System.out.println(title);
				
				dao.insert(new BoardDTO(0, writer, title, contents, null, 0));
				response.sendRedirect("/list.board");

				/* 게시글 상세보기 */
			} else if (cmd.equals("/detail.board")) {
				
				int target_seq = Integer.parseInt(request.getParameter("seq"));
				
				BoardDTO dto = dao.selectContent(target_seq);
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/board/detail.jsp").forward(request, response);

				/* 내 글 삭제하기 */
			} else if (cmd.equals("/delete.board")) {
				
				int target_seq = Integer.parseInt(request.getParameter("seq"));
				
				dao.delete(target_seq);
				response.sendRedirect("/list.board"); // DB거쳐야 해서 .board 작성
				
				/* 내 글 수정하기 */
			} else if (cmd.equals("/edit.board")) {
				
				int target_seq = Integer.parseInt(request.getParameter("seq"));
				System.out.println(target_seq);
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				
				int result = dao.edit(target_seq, title, contents);
				request.setAttribute("result", result);
//				request.getRequestDispatcher("/detail.board?seq="+target_seq).forward(request, response);
				request.getRequestDispatcher("/list.board").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
