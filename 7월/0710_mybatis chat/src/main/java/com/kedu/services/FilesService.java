package com.kedu.services;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dao.BoardDAO;
import com.kedu.dao.FilesDAO;
import com.kedu.dto.BoardDTO;
import com.kedu.dto.FilesDTO;

@Service
public class FilesService {

	@Autowired
	private FilesDAO fdao;
	
	@Autowired
	private BoardDAO bdao;
	
	/** 파일 업로드 **/
	@Transactional
	public void upload(BoardDTO dto, String realPath, MultipartFile[] files) throws Exception {
		System.out.println(dto.getSeq());
		int parent_seq = bdao.insert(dto);
		System.out.println(parent_seq);
		
		File realPathFile = new File(realPath);
		if(!realPathFile.exists()) realPathFile.mkdir(); 
		
		for(MultipartFile file : files) {
			if(file.getSize() == 0) continue; // 업로드한 파일이 없어도 계속 진행 
			
			String oriName = file.getOriginalFilename();
			String sysName = UUID.randomUUID() + "_" + oriName;
			file.transferTo(new File(realPath+"/"+sysName));
			
			fdao.insert(oriName, sysName, parent_seq);
		}
		
	}

	/** 파일 출력 **/
	public List<FilesDTO> listFiles(int seq) throws Exception {
		return fdao.selectAll(seq);
	}
	
	
}
