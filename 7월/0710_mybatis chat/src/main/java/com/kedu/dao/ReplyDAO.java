package com.kedu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kedu.dto.ReplyDTO;

@Repository
public class ReplyDAO {

	
	@Autowired
	private SqlSession mybatis;
	
	/** 댓글 입력 **/
	public int insert(ReplyDTO dto) throws Exception {
		return mybatis.insert("reply.insert", dto);
	}
	
	/** 댓글 출력 **/
	public List<ReplyDTO> selectByParentSeq(int parent_seq) throws Exception {
		return mybatis.selectList("reply.selectByParentSeq", parent_seq);
	}
	
	/** 댓글 삭제 **/
	public int delete(int seq) throws Exception {
		return mybatis.delete("reply.delete", seq);
	}
	
	/** 댓글 작성 취소 **/
	public String cancel(int seq) throws Exception {
		return mybatis.selectOne("reply.cancel", seq);
	}
	
	/** 댓글 수정 **/
	public int update(int seq, String contents) throws Exception {
		
		Map<String, Object> params = new HashMap<>();
		params.put("seq", seq);
		params.put("contents", contents);
		
		return mybatis.update("reply.update", params);
	}
	
	
// =================[ Spring JDBC ]=================		
//	@Autowired
//	private JdbcTemplate jdbc;
//	
//	/**
//	 * 댓글 입력
//	 * @param dto
//	 * @return
//	 * @throws Exception
//	 */
//	public int insert(ReplyDTO dto) throws Exception {
//		String sql = "insert into reply values(reply_seq.nextval, ?, ?, sysdate, ?)";
//		return jdbc.update(sql, dto.getWriter(), dto.getContents(), dto.getParent_seq());
//	}
//	
//	/**
//	 * 댓글 출력
//	 * @param parent_seq
//	 * @return
//	 * @throws Exception
//	 */
//	public List<ReplyDTO> selectByParentSeq(int parent_seq) throws Exception {
//		String sql = "select * from reply where parent_seq=?";
//		return jdbc.query(sql, new BeanPropertyRowMapper<>(ReplyDTO.class), parent_seq);
//	}
//	
//	
//	/**
//	 * 댓글 삭제
//	 * @param seq
//	 * @return
//	 * @throws Exception
//	 */
//	public int delete(int seq) throws Exception {
//		String sql = "delete from reply where seq = ?";
//		return jdbc.update(sql, seq);
//	}
//	
//	/**
//	 * 댓글 작성 취소
//	 * @param seq
//	 * @return
//	 * @throws Exception
//	 */
//	public String cancel(int seq) throws Exception {
//		String sql = "select contents from reply where seq = ?";
//		return jdbc.queryForObject(sql, String.class, seq);
//	}
//	
//	/**
//	 * 댓글 수정
//	 * @param seq
//	 * @param contents
//	 * @return
//	 * @throws Exception
//	 */
//	public int update(int seq, String contents) throws Exception {
//		String sql = "update reply set contents=?, write_date=sysdate where seq = ?";
//		return jdbc.update(sql, contents,seq);
//	}
	

}
