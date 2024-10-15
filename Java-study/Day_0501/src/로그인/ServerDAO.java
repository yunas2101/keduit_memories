package 로그인;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServerDAO {

	/**
	 * DB연결
	 * @return
	 * @throws Exception
	 */
	private Connection getConnection() throws Exception {
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "kedu";
		String dbPW = "kedu";
		return DriverManager.getConnection(dbURL, dbID, dbPW);
	}
	
	
	/**
	 * 1.회원가입
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int joinMembers(ServerDTO dto) throws Exception {
		
		String sql = "insert into members values(?,?,?)";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
		
		pstat.setString(1, dto.getId());
		pstat.setString(2, dto.getPw());
		pstat.setString(3, dto.getName());
					
		int result = pstat.executeUpdate();
		return result;
		
//		if(result > 0) {
//			return "회원가입 성공";
//		} else {
//			return "회원가입 실패";
//		}
		
		}
	}
	
	
	/**
	 * 2. 로그인
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public boolean addMembers(String id, String pw) throws Exception {
		
	String sql = "select * from members where id = ? And pw = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
		
			pstat.setString(1, id);
			pstat.setString(2, pw);
			
			try(ResultSet rs = pstat.executeQuery()){

				boolean result = rs.next();
				return result;
				
//				if(rs.next()) {
//					return "로그인 성공";
//				}else {
//					return "로그인 실패";
//				}
			}
		}
		
	}
	
}
