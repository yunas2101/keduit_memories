import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import oracle.jdbc.proxy.annotation.Pre;

public class CafeDAO {

	/**
	 * DB연결
	 * 
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
	 * 1. DB에 저장
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int addCafe(CafeDTO dto) throws Exception {
		String sql = "insert into cafe values(cafe_seq.nextval, ?, ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, dto.getName());
			pstat.setInt(2, dto.getPrice());

			int result = pstat.executeUpdate();
			return result;
		}

	}

	/**
	 * 2. DB에 저장되어 있는 cafe메뉴들 출력
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CafeDTO> selectAll() throws Exception {
		String sql = "select * from cafe";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {

			ArrayList<CafeDTO> list = new ArrayList<CafeDTO>();

			while (rs.next()) {

				int id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);

				CafeDTO dto = new CafeDTO(id, name, price);
				list.add(dto);
			}
			return list;
		}

	}

	/**
	 * 3. 이름 검색받아서 목록 출력
	 * 
	 * @param targetName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CafeDTO> searchMenu(String targetName) throws Exception {

		String sql = "select * from cafe where name like ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, "%" + targetName + "%");

			try (ResultSet rs = pstat.executeQuery()) {

				ArrayList<CafeDTO> list = new ArrayList<CafeDTO>();

				while (rs.next()) {

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

	/**
	 * 4. 삭제할 id 받고 삭제
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteMenu(int id) throws Exception {

		String sql = "delete from cafe where id = ?";

		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			
			return result;
		}
	}
	
	/**
	 * 5-1. 수정할 id 있는지 확인
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isIdExist(int id) throws Exception {
		
		String sql = "select * from cafe where id = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setInt(1, id);
			
			try(ResultSet rs = pstat.executeQuery()){
				
				return rs.next();
				
			}
			
		}
		
	}
	
	/**
	 * 5-2. 수정할 id받고, 이름&가격 수정 후 성공 반환
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int updateMenu (int id, String name, int price) throws Exception {
		
		String sql = "update cafe set name = ?, price = ? where id = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setString(1, name);
			pstat.setInt(2, price);
			pstat.setInt(3, id);
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	

}
