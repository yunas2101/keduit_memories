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

import com.kedu.dto.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	
	/**
	 * 글 쓴 목록 출력
	 * @return
	 * @throws Exception
	 */
//	public List<BoardDTO> selectAll() throws Exception {
//		String sql = "select * from board order by seq desc";
//		return jdbc.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));
//	}
	
	public List<BoardDTO> selectAll(int cpage, int record_count_per_page) throws Exception {
		String sql = "select a.* from (SELECT  board.*, ROW_NUMBER() OVER (ORDER BY seq DESC) AS rown FROM board)a where rown between ? and ?";
		return jdbc.query(sql, 
				new BeanPropertyRowMapper<>(BoardDTO.class), 
				cpage*record_count_per_page - (record_count_per_page-1), 
				cpage*record_count_per_page);
	}
	

	/**
	 * DB에 작성글 넣어주고, 게시판 seq값 반환 (Files의 parent_seq)
	 * @param title
	 * @param contents
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public int insert(BoardDTO dto) throws Exception {
		String sql = "insert into board values(board_seq.nextval, ?, ?, ?, sysdate, 0)";
		
		KeyHolder key = new GeneratedKeyHolder();
		
		jdbc.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"seq"});
				ps.setString(1, dto.getTitle());
				ps.setString(2, dto.getContents());
				ps.setString(3, dto.getWriter());
				
				return ps;
			}
		}, key);
		
		int seq = key.getKey().intValue();
		System.out.println(seq);
		return seq;
				
	}
	
	
	/**
	 * title로 선택된 게시글 상세보기
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public BoardDTO selectBoard(int seq) throws Exception {
		String sql = "select * from board where seq =?";
		return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), seq);
	}
	
	/**
	 * 조회수 증가
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public int updateViewCount(int seq) throws Exception {
		String sql = "update board set view_count = view_count +1 where seq = ?";
		return jdbc.update(sql, seq);
	}
	
	
	/**
	 * 게시글 제목,내용 수정
	 * @param title
	 * @param contents
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(String title, String contents, int seq) throws Exception {
		String sql = "update board set title =?, contents =? where seq =?";
		return jdbc.update(sql, title, contents, seq);
	}
	
	/**
	 * 게시글 삭제
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(int seq) throws Exception {
		String sql = "delete from board where seq =?";
		return jdbc.update(sql, seq);
	}
	
	/**
	 * 게시글 총 갯수 (페이징 사용)
	 * @return
	 * @throws Exception
	 */
	public int getBoardCount() throws Exception {
		String sql = "select count(*) from board";
		return jdbc.queryForObject(sql, Integer.class);
	}
	
	
	
	
	
	
	
	
	
	
}
