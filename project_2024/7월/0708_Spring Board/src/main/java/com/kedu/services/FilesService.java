package com.kedu.services;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dao.FilesDAO;
import com.kedu.dto.FilesDTO;

@Service
public class FilesService {

	@Autowired
	private FilesDAO dao;
	
	/** 파일 업로드 **/
	public void upload(String realPath, MultipartFile[] files, int parent_seq) throws Exception {
		
		File realPathFile = new File(realPath);
		if(!realPathFile.exists()) realPathFile.mkdir(); 
		
		for(MultipartFile file : files) {
			if(file.getSize() == 0) continue; // 업로드한 파일이 없어도 계속 진행 
			
			String oriName = file.getOriginalFilename();
			String sysName = UUID.randomUUID() + "_" + oriName;
			file.transferTo(new File(realPath+"/"+sysName));
			
			dao.insert(oriName, sysName, parent_seq);
		}
		
	}

	/** 파일 출력 **/
	public List<FilesDTO> listFiles(int seq) throws Exception {
		return dao.selectAll(seq);
	}
	
	
}
