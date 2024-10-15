package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ReplyDTO;

public class ReplyDAO {
	
	/**
	 * DB 연결
	 * 
	 * @return
	 * @throws Exception
	 */
	// JNDI - Tomcat 서버에게 DBCP 인스턴스를 생성해 줄 것을 요구.
	private static ReplyDAO instance;

	public synchronized static ReplyDAO getInstance() {
		if (instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	private ReplyDAO() {}
	
	/**
	 * 댓글 입력
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public Timestamp insert(ReplyDTO dto) throws Exception {
		
		String sql = "insert into reply values(reply_seq.nextval,?,?,sysdate,?)";
		Timestamp sysdate=null;
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql, new String[] {"write_date"})){
			
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getContents());
			pstat.setInt(3, dto.getParent_seq());
			
			pstat.executeUpdate();
			try (ResultSet rs = pstat.getGeneratedKeys()){
				rs.next();
				sysdate=rs.getTimestamp(1);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		return sysdate;
	}
	
	
	/**
	 * 댓글 출력
	 * @param target_seq
	 * @return
	 * @throws Exception
	 */
	public List<ReplyDTO> selectReply(int target_seq) throws Exception {
		
		String sql = "select * from reply where parent_seq = ? order by 1 desc";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setInt(1, target_seq);
			
			try(ResultSet rs = pstat.executeQuery()){
				
				List<ReplyDTO> list = new ArrayList<ReplyDTO>();
				while(rs.next()) {
					
					int seq = rs.getInt("seq");
					String writer = rs.getString("writer");
					String contents = rs.getString("contents");
					Timestamp write_date = rs.getTimestamp("write_date");
					int parent_seq = rs.getInt("parent_seq");
							
					list.add(new ReplyDTO(seq, writer, contents, write_date, parent_seq));		
					
				}
				return list;
			}
			
		}
	}
	
	/**
	 * 댓글 삭제
	 * @param target_seq
	 * @return
	 * @throws Exception
	 */
	public int deleteReply(int target_seq) throws Exception {
		
		String sql = "delete from reply where seq = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setInt(1, target_seq);
			return pstat.executeUpdate();
		}
		
	}
	
	/**
	 * 댓글 수정
	 * @param hidden_reply_seq
	 * @param reply_contents
	 * @return
	 * @throws Exception
	 */
	public int edit(int hidden_reply_seq, String reply_contents) throws Exception {
		
		String sql = "update reply set contents = ? where seq = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
		
		pstat.setString(1, reply_contents);	
		pstat.setInt(2, hidden_reply_seq);
		
		return pstat.executeUpdate();
		
		}
		
		
	}
	
	
	
	
	

}
