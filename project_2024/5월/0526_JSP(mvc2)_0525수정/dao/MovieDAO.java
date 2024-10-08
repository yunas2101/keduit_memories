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

import dto.MovieDTO;

public class MovieDAO {

	public static MovieDAO instance;
	public synchronized static MovieDAO getInstance() {
		if(instance == null) {
			instance = new MovieDAO();
		}
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
				return ds.getConnection();
	}
	
	private MovieDAO() {}
	
	/**
	 * DB에 값 저장
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(MovieDTO dto) throws Exception {
		String sql = "insert into movie values(movie_seq.nextval, ?, ?, sysdate)";
	
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	/**
	 * DB 내용 출력
	 * @return
	 * @throws Exception
	 */
	public List<MovieDTO> selectAll() throws Exception {
		
		String sql = "select * from movie";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			
			List<MovieDTO> list = new ArrayList<MovieDTO>();
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String genre = rs.getString(3);
				Timestamp movie_date = rs.getTimestamp(4);
				
				list.add(new MovieDTO(id, title, genre, movie_date));
			}
			return list;
		}
	}
	
	/**
	 * id 받고 DB에서 삭제
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(String id) throws Exception {
		
		String sql = "delete from movie where id = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setInt(1, Integer.parseInt(id));
			
			int result = pstat.executeUpdate();
			return result;
		}
		
		
	}
	
	public int update (String id, String title, String genre) throws Exception {
		
		String sql = "update movie set title =?, genre =?, movie_date = sysdate where id =?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setString(1, title);
			pstat.setString(2, genre);
			pstat.setInt(3, Integer.parseInt(id));
			
			int result = pstat.executeUpdate();
			return result;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
