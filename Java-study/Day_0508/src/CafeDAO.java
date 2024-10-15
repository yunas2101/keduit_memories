import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CafeDAO {

	/**
	 * DB 연결
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
	 * 1. 메뉴 추가 후 성공 반환
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int addCafe(CafeDTO dto) throws Exception {
		
		String sql = "insert into cafe values(cafe_seq.nextval,?,?)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
				){
			
			pstat.setString(1, dto.getName());
			pstat.setInt(2, dto.getPrice());
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public ArrayList<CafeDTO> selectAll() throws Exception {
		
		String sql = "selcet * from cafe";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
						ResultSet rs = pstat.executeQuery()){
			
			ArrayList<CafeDTO> list = new ArrayList<CafeDTO>();
			
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				
				CafeDTO dto = new CafeDTO(id, name, price);
				list.add(dto);
				
			}
			return list;
		}
		
	}
	
	
}
