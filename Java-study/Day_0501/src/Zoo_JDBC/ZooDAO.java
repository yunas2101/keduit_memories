package Zoo_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ZooDAO {

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
	 * 1. DB에 저장 후 성공 반환
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int addZoo(ZooDTO dto) throws Exception {

		String sql = "insert into zoo values(zoo_seq.nextval,?,?)";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){

			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getColor());

			int result = pstat.executeUpdate();

			return result;
		}
	}


	/**
	 * 2. 목록에 있는 정보 메인에서 출력
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ZooDTO> seletAll() throws Exception {

		String sql = "select * from zoo";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();
				){

			ArrayList<ZooDTO> list = new ArrayList<ZooDTO>();

			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String color = rs.getString(3);

				ZooDTO dto = new ZooDTO(id,name,color);
				list.add(dto);
			}

			return list;
		}
	}

	/**
	 * 3. 검색한 name 받고, 이름 포함된 정보 출력
	 * @param targetName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ZooDTO> searchName(String targetName) throws Exception {
		
		String sql = "select * from zoo where name like ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			
			
			pstat.setString(1, "%" + targetName + "%");
			
			try(ResultSet rs = pstat.executeQuery()){
				
				ArrayList<ZooDTO> list = new ArrayList<ZooDTO>();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String color = rs.getString(3);
					
					ZooDTO dto = new ZooDTO(id, name, color);
					list.add(dto);
				}
				return list;
			}
		}
		 
		
	}
	
	/**
	 * 4. 삭제할 id 받고, 삭제 후 성공 반환
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteZoo(int id) throws Exception {
		
		String sql = "delete from zoo where id = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			
			return result;
		}
		
	}
	
	
	/**
	 * 5-1. 수정할id받고 목록에 있는지 확인
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isIdExist(int id) throws Exception {
		
		String sql = "select * from zoo where id = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
		
			pstat.setInt(1, id);
			
			try(ResultSet rs = pstat.executeQuery()){
				
				return rs.next();
			}
		}
		
	}
	
	/**
	 * 5-2. 수정할 id 받고, 이름&색깔 수정 후 성공 반환
	 * @param id
	 * @param name
	 * @param color
	 * @return
	 * @throws Exception
	 */
	public int updateZoo(int id, String name, String color) throws Exception{
		
		String sql = "update zoo set name = ?, color = ? where id = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
		
			pstat.setString(1, name);
			pstat.setString(2, color);
			pstat.setInt(3, id);
			
		
			int result = pstat.executeUpdate();
			return result;
			
		}
		
		
	}
	
	


}




