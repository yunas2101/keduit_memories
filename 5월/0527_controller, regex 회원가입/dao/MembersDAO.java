package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MembersDTO;

public class MembersDAO {

	/**
	 * DB 연결
	 * 
	 * @return
	 * @throws Exception
	 */
	// JNDI - Tomcat 서버에게 DBCP 인스턴스를 생성해 줄 것을 요구.
	private static MembersDAO instance;
	public synchronized static MembersDAO getInstance() {
		if(instance == null) {
			instance = new MembersDAO();
		}
		return instance;
	}
	

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	private MembersDAO() {}
	
	
	/**
	 * id 값 받고, DB의 id와 중복체크 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean idcheck(String id) throws Exception {
		
		String sql = "select * from members where id =?";
		System.out.println(id);
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setString(1, id);
			
			try(ResultSet rs = pstat.executeQuery()){
				
				return rs.next();
			}
			
		}
		
	}
	
	
	/**
	 * DB에 값 넣기
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(MembersDTO dto) throws Exception {
		
		String sql = "insert into members values(?,?,?,?,?,?,?,?,sysdate)";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getPhone());
			pstat.setString(5, dto.getEmail());
			pstat.setString(6, dto.getZipcode());
			pstat.setString(7, dto.getAddress1());
			pstat.setString(8, dto.getAddress2());
			
			
			int result = pstat.executeUpdate();
			return result;
		}
		
		
	}
	
}
