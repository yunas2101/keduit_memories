package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ReplyDAO;
import dto.ReplyDTO;

@WebServlet("*.reply")
public class ReplyController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		ReplyDAO dao = ReplyDAO.getInstance();
		Gson g = new Gson();

		String cmd = request.getRequestURI();
		System.out.println("사용자 요청 : " + cmd);

		/* 댓글 출력은 BoardController */

		try {
			/* 댓글 작성 */
			if (cmd.equals("/insert.reply")) {

				HttpSession session = request.getSession();

				String writer = (String) request.getSession().getAttribute("loginID");
				String contents = request.getParameter("reply_contents");
				int parent_seq = Integer.parseInt(request.getParameter("seq"));

				System.out.println(writer + " " + contents + " " + parent_seq);

				ReplyDTO dto = new ReplyDTO(0, writer, contents, null, parent_seq);
				Timestamp sysdate= dao.insert(dto);
				
				dto.setWrite_date(sysdate);
				String result = g.toJson(dto);
				PrintWriter pw = response.getWriter();
				pw.append(result);
				
				//response.sendRedirect("/detail.board?seq=" + parent_seq);

				
				/* 댓글 출력 */
			} else if (cmd.equals("/list.reply")) {

				int target_seq = Integer.parseInt(request.getParameter("seq"));
				System.out.println(target_seq);
				
				List<ReplyDTO> replyList = dao.selectReply(target_seq);
				request.setAttribute("reply", replyList);
			
				
				request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
				
				
				/* 댓글 삭제 */
			} else if (cmd.equals("/delete.reply")) {
				int target_seq = Integer.parseInt(request.getParameter("seq")); // reply의 seq받기
				int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));

				dao.deleteReply(reply_seq);

				response.sendRedirect("/detail.board?seq=" + target_seq);

				/* 댓글 수정 */
			} else if (cmd.equals("/edit.reply")) {

				int hidden_reply_seq = Integer.parseInt(request.getParameter("hidden_reply_seq"));
				int hidden_reply_parentseq = Integer.parseInt(request.getParameter("hidden_reply_parentseq"));
				String reply_contents = request.getParameter("hidden_reply_contents");

				dao.edit(hidden_reply_seq, reply_contents);
				response.sendRedirect("/detail.board?seq=" + hidden_reply_parentseq);
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
