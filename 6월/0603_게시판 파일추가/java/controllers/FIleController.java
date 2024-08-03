package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.FilesDAO;
import dto.FilesDTO;

@WebServlet("*.file")
public class FIleController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String cmd = request.getRequestURI();
		FilesDAO dao = FilesDAO.getInstance();

		try {

			/* 파일 업로드 */
//			if (cmd.equals("/upload.file")) {
//
//				int maxSize = 1024 * 1024 * 10; // 10MB 사이즈 제한
//				String realPath = request.getServletContext().getRealPath("files"); // 파일이 저장될 위치 (files라는 폴더에 넣겠다)
//				File uploadPath = new File(realPath); // 저장 위치 폴더를 파일 인스턴스로 생성
//
//				if (!uploadPath.exists()) { // 파일 업로드 할 폴더가 존재하지 않는다면
//					uploadPath.mkdir(); // 폴더를 만들겠다.
//				}
//
//				MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF8",
//						new DefaultFileRenamePolicy());
//				
//				
//				Enumeration<String> names = multi.getFileNames();
//				while(names.hasMoreElements()) {
//					
//					String name = names.nextElement();
//					String oriName = multi.getOriginalFileName(name); // 원본 파일 이름
//					String sysName = multi.getFilesystemName(name); // 서버에 저장된 파일의 이름
//
//					if(oriName!=null) {
//						dao.insert(new FilesDTO(0, oriName, sysName, 0));
//					}
//				}
//				
////				response.sendRedirect("/board/writeform.jsp");
//				response.sendRedirect("/write.board");
//				
//
//				
//			} 
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}


	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
