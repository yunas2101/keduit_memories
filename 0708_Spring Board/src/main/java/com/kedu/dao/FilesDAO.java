package com.kedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kedu.dto.FilesDTO;

@Repository
public class FilesDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	/**
	 * DB에 파일 insert
	 * @param sysname
	 * @param oriname
	 * @return
	 * @throws Exception
	 */
	public int insert(String sysname, String oriname, int parent_seq) throws Exception {
		String sql = "insert into files values(files_seq.nextval, ?, ?, ?)";
		return jdbc.update(sql, sysname, oriname, parent_seq);
		
	}
	
	
	/**
	 * 파일 출력
	 * @return
	 */
	public List<FilesDTO> selectAll(int seq) {
		String sql = "select * from files where parent_seq = ?";
		return jdbc.query(sql, new BeanPropertyRowMapper<>(FilesDTO.class), seq);

	}
}
