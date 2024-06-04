package controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import commons.BoardConfig;
import dao.BoardDAO;
import dao.FilesDAO;
import dao.ReplyDAO;
import dto.BoardDTO;
import dto.FilesDTO;
import dto.ReplyDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		BoardDAO dao = BoardDAO.getInstance();
		ReplyDAO replydao = ReplyDAO.getInstance();
		FilesDAO filesdao = FilesDAO.getInstance();
		Gson g = new Gson();

		String cmd = request.getRequestURI();
		System.out.println("사용자 요청 : " + cmd);

		try {

			/* 리스트 목록 */
			if (cmd.equals("/list.board")) {
				
				String pcpage = request.getParameter("cpage");
				if(pcpage == null) {pcpage = "1";} // 페이지 번호 없으면 1페이지로 가라

				int cpage = Integer.parseInt(pcpage);
				
//				List<BoardDTO> list = dao.selectAll();
				
				// 1 : 1~10
				// 2 : 11~20
				List<BoardDTO> list = 
						dao.selectNtoM(
								cpage*BoardConfig.RECORD_COUNT_PER_PAGE - (BoardConfig.RECORD_COUNT_PER_PAGE - 1), 
								cpage*BoardConfig.RECORD_COUNT_PER_PAGE); // 몇번부터 몇번까지
				
				request.setAttribute("list", list); // 현재 내가 있는 목록 페이지의 게시글만 가져오는 것
				request.setAttribute("cpage", cpage); // 현재 페이지
				request.setAttribute("record_count_per_page", BoardConfig.RECORD_COUNT_PER_PAGE); // 보여질 게시글의 수
				request.setAttribute("navi_count_per_page", BoardConfig.NAVI_COUNT_PER_PAGE); // 네비게이터 갯수
				request.setAttribute("record_total_count", dao.getRecordCount()); // 총몇개의 레코드?			
				
//				String pageNavi = dao.getPageNavi(cpage);
//				request.setAttribute("navi", pageNavi);
				
				request.getRequestDispatcher("/board/list.jsp").forward(request, response);

				
				/* 글 작성 */
			} else if (cmd.equals("/write.board")) {

				String writer = (String) request.getSession().getAttribute("loginID");
				
				//--- 파일 업로드
				int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
				String realPath = request.getServletContext().getRealPath("files"); // 파일이 저장될 위치
				File uploadPath = new File(realPath); // 저장 위치 폴더를 파일 인스턴스로 생성

				if (!uploadPath.exists()) { // 파일 업로드 할 폴더가 존재하지 않는다면
					uploadPath.mkdir(); // 폴더를 만들겠다.
				}

				MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF8",
						new DefaultFileRenamePolicy());
				
				String title = multi.getParameter("title");
				String contents = multi.getParameter("contents");
				int seq = dao.insert(new BoardDTO(0, writer, title, contents, null, 0));
				
				Enumeration<String> names = multi.getFileNames();
				while(names.hasMoreElements()) {
					
					String name = names.nextElement();
					String oriName = multi.getOriginalFileName(name); // 원본 파일 이름
					String sysName = multi.getFilesystemName(name); // 서버에 저장된 파일의 이름

					if(oriName!=null) {
						filesdao.insert(new FilesDTO(0, oriName, sysName, seq));
					}
				}
				
				response.sendRedirect("/list.board");
				

				/* 게시글 상세보기 & 댓글 출력 */
			} else if (cmd.equals("/detail.board")) {
				
				int target_seq = Integer.parseInt(request.getParameter("seq"));
				
				dao.updateViewCount(target_seq);
				BoardDTO dto = dao.selectContent(target_seq);
				request.setAttribute("dto", dto);
				

				/* 파일 출력 */
				List<FilesDTO> fileList = filesdao.selectFile(target_seq);
				request.setAttribute("files", fileList);
//				System.out.println(fileList.get(0).getSeq());
				
				request.getRequestDispatcher("/list.reply").forward(request, response);
				
				

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
				response.sendRedirect("/detail.board?seq="+target_seq);
				
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
