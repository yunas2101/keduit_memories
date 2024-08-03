package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.FilesDTO;

public class FilesDAO {
	/**
	 * DB 연결
	 * 
	 * @return
	 * @throws Exception
	 */
	// JNDI - Tomcat 서버에게 DBCP 인스턴스를 생성해 줄 것을 요구.
	private static FilesDAO instance;

	public synchronized static FilesDAO getInstance() {
		if (instance == null) {
			instance = new FilesDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	private FilesDAO() {
	}
	
	
	/**
	 * 파일 업로드
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(FilesDTO dto) throws Exception {
		
		String sql = "insert into files values(files_seq.nextval, ?, ?, ?)";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)) {
			
			pstat.setString(1, dto.getOriname());
			pstat.setString(2, dto.getSysname());
			pstat.setInt(3, dto.getParent_seq());
			
			
			int result = pstat.executeUpdate();
			return result;
		
		}
	}
	
	
	/**
	 * 파일 출력
	 * @param parent_seq
	 * @return
	 * @throws Exception
	 */
	public List<FilesDTO> selectFile(int parent_seq) throws Exception {
	
	String sql  = "select * from files where parent_seq = ?";
	
	try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql)){
		
		pstat.setInt(1, parent_seq);
		
		try(ResultSet rs = pstat.executeQuery()){
			
			List<FilesDTO> list = new ArrayList<FilesDTO>();
			
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String oriname = rs.getString("oriname");
				String sysname = rs.getString("sysname");
				
				list.add(new FilesDTO(seq, oriname, sysname,parent_seq));
			}
			
			return list;
		}
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
}
