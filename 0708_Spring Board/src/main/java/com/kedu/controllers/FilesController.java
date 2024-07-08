package com.kedu.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dto.FilesDTO;
import com.kedu.services.FilesService;

@Controller
@RequestMapping("/file/")
public class FilesController {

	@Autowired
	private HttpSession session;
	@Autowired
	private FilesService serv;
	
	/** 파일 출력 **/
	@ResponseBody
	@RequestMapping("list")
	public List<FilesDTO> list(int seq) throws Exception {
		return serv.listFiles(seq);
	}
	
	/** 파일 다운로드 **/
	@RequestMapping("download")
	public void download(String oriname, String sysname, HttpServletResponse response) throws Exception {
		
		String realPath = session.getServletContext().getRealPath("upload"); 
		File target = new File(realPath + "/" + sysname); // 파일의 위치 + 다운 받을 파일 이름
		
		oriname = new String(oriname.getBytes(),"ISO-8859-1"); // chrome 인코딩 처리
		response.setHeader("content-disposition", "attachment;filename=\""+oriname+"\"");
		// 응답 데이터가 첨부파일임을 알림, 다운로드 파일 이름 세팅
		
		try(DataInputStream dis = new DataInputStream(new FileInputStream(target)); // 파일에서 내용 뽑아오기
				DataOutputStream dos = new DataOutputStream(response.getOutputStream())){ // 네트워크 방향으로 출력하기
			
			byte[] fileContents = new byte[(int)target.length()];
			dis.readFully(fileContents);
			dos.write(fileContents);
			dos.flush();
			
		}
	}
	
	
	
	@ExceptionHandler(Exception.class) // 모든 종류의 예외
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
		
	}
	
}
