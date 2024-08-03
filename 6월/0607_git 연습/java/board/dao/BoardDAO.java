package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.dto.BoardDTO;

public class BoardDAO {
private static BoardDAO instance;
	
	public static BoardDAO  getInstance() {
		if(instance==null) instance=new BoardDAO();
		return instance;
	}
	
	private BoardDAO() {}
	
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource db = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return db.getConnection();
	}
	
	public List<BoardDTO> selectAll(){
		List<BoardDTO> list = new ArrayList<>();
		String sql="select *from board";
		try (Connection con=this.getConnection();
				PreparedStatement pstat= con.prepareStatement(sql);
				ResultSet rs=pstat.executeQuery()){
			while(rs.next()) {
				int seq=rs.getInt(1);
				String title=rs.getString(2);
				String contents=rs.getString(3);
				int count =rs.getInt(4);
				String member_id=rs.getString(5);
				Timestamp write_date=rs.getTimestamp(6);
				
				list.add(new BoardDTO(seq,title,contents,count,member_id,write_date));
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
}
