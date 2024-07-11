package com.kedu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.dto.FilesDTO;

@Repository
public class FilesDAO {
	
	@Autowired
	private SqlSession mybatis;
	
	
	/** DB에 파일 insert **/
	public int insert(String oriname, String sysname, int parent_seq) throws Exception {
	
		Map<String, Object> params = new HashMap<>();
		params.put("sysname", sysname);
		params.put("oriname", oriname);
		params.put("parent_seq", parent_seq);
		
		return mybatis.insert("files.insert", params);

	}

	
//	/**
//	 * 파일 출력
//	 * @return
//	 */
	public List<FilesDTO> selectAll(int seq) {
		return mybatis.selectList("files.selectAll", seq);

	}
	
	
	
	
// =================[ Spring JDBC ]=================
	
//	@Autowired
//	private JdbcTemplate jdbc;
//	
//	/**
//	 * DB에 파일 insert
//	 * @param sysname
//	 * @param oriname
//	 * @return
//	 * @throws Exception
//	 */
//	public int insert(String sysname, String oriname, int parent_seq) throws Exception {
//		String sql = "insert into files values(files_seq.nextval, ?, ?, ?)";
//		return jdbc.update(sql, sysname, oriname, parent_seq);
//		
//	}
//	
//	
//	/**
//	 * 파일 출력
//	 * @return
//	 */
//	public List<FilesDTO> selectAll(int seq) {
//		String sql = "select * from files where parent_seq = ?";
//		return jdbc.query(sql, new BeanPropertyRowMapper<>(FilesDTO.class), seq);
//
//	}
}
