import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CafeDAO {

	private String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbID = "kedu";
	private String dbPW = "kedu";

	/**
	 * DB 연결
	 */
	private Connection getConnection() throws Exception {
		return DriverManager.getConnection(dbURL, dbPW, dbID);

	}

	/**
	 * 1. 받은 메뉴를 "DB"에 저장 후 성공 반환
	 */
	public int addCafe(CafeDTO dto) throws Exception {

		try(	Connection con = this.getConnection();
				Statement stat = con.createStatement();
				){
			String sql = "insert into cafe values (cafe_seq.nextval, '"+ dto.getName() +"', "+dto.getPrice()+ " )";
			int result = stat.executeUpdate(sql);

			return result;
		}

	}

	/**
	 * 2. 목록 출력 
	 */
	public ArrayList<CafeDTO> selectAll() throws Exception{

		String sql = "select * from cafe";

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				){
			ArrayList<CafeDTO> list = new ArrayList<CafeDTO>();

			while(rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				CafeDTO dto = new CafeDTO(id, name, price);
				list.add(dto);

			}
			return list;
		}

	}


	/**
	 * 3. 이름 받아서 검색 후 출력
	 */
	public ArrayList<CafeDTO> searchName (String targetName) throws Exception {

		String sql = "select * from cafe where name like '%"+ targetName +"%'";

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql)
				){

			ArrayList<CafeDTO> list = new ArrayList<CafeDTO>();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				CafeDTO dto = new CafeDTO(id,name,price);
				list.add(dto);
			}
			return list;
		}

	}


	/**
	 * 4. 삭제할 id 받아서, 삭제 후 성공 반환
	 */
	public int deleteCafe(int id) throws Exception {

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				){
			String sql = "delete from cafe where id = "+id;
			int result = stat.executeUpdate(sql);
			return result;
		}

	}

	/**
	 * 5-1. 수정할 대상의 id 있는지 확인 출력 
	 */
	public boolean isIdExist(int id) throws Exception {

		String sql = "select * from cafe where id = " + id;

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql)
				){

			boolean result = rs.next();
			return result;

		}

	}


	/**
	 * 5-2. 수정할 id 받고, 이름&가격 받고 수정 후 성공 반환
	 */
	public int updateCafe(int id, String name, int price) throws Exception {

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();){

			String sql = "update cafe set name = '"+ name +"', price = "+ price +" where id = " + id;
			
			int result = stat.executeUpdate(sql);
			return result;
		
			
		}
	}






}
