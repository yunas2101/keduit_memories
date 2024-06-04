package controllers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

			/* 파일 다운로드 */
			if (cmd.equals("/download.file")) {

				String filepath = request.getServletContext().getRealPath("files"); // 다운받을 파일의 위치 확보
				String sysname = request.getParameter("sysname"); // 다운받을 이름 확보
				String oriname = request.getParameter("oriname");
				
				oriname = new String(oriname.getBytes("UTF8"), "ISO-8859-1");
			
				response.reset(); // 기존에 response가 가지고 있는 내용을 리셋하는 작업
				response.setHeader("Content-Disposition", "attachment;filename=\""+oriname+"\"");

				File target = new File(filepath + "/" + sysname); // 위치와 이름을 결합하여 타겟 파일 인스턴스 생성
				
				byte[] fileContents = new byte[(int)target.length()]; // 하드디스크에서 뽑아낸 타겟 파일 내용을 저장할 배열을 준비
				
				try(FileInputStream fis = new FileInputStream(target); 
						DataInputStream dis = new DataInputStream(fis);
						ServletOutputStream sos = response.getOutputStream();){
					dis.readFully(fileContents);
					sos.write(fileContents); // 파일의 내용을 전송
					sos.flush();
				}

				
			} 
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}


	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
