import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BakeryDAO {

	private String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbID = "kedu";
	private String dbPW = "kedu";


	/**
	 * DB 연결
	 */
	private Connection getConnection() throws Exception{
		return DriverManager.getConnection(dbURL, dbPW, dbID);

	}

	/**
	 * 1. 받은 메뉴 정보 "DB"에 저장 후 성공 반환
	 */
	public int addBakery(BakeryDTO dto) throws Exception {

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();){

			String sql = "insert into bakery values(bakery_seq.nextval, '"+ dto.getName() +"', "+ dto.getPrice() +" )";
			int result = stat.executeUpdate(sql);
			return result;
		}
	}

	/**
	 * 2. 목록 출력
	 */
	public ArrayList<BakeryDTO> selectAll() throws Exception {

		String sql = "select * from bakery";

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql)){

			ArrayList<BakeryDTO> list = new ArrayList<BakeryDTO>();

			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				BakeryDTO dto = new BakeryDTO(id, name, price);
				list.add(dto);
			}
			return list;
		}
	}

	/**
	 * 검색할 이름 받고, 포함된 이름이 목록에 있다면 출력
	 */
	public ArrayList<BakeryDTO> searchName (String targetName) throws Exception {

		String sql = "select * from bakery where name like '%"+ targetName +"%'";

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql)){

			ArrayList<BakeryDTO> list = new ArrayList<BakeryDTO>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				BakeryDTO dto = new BakeryDTO(id, name, price);
				list.add(dto);
			}
			return list;
		}

	}
	
	/**
	 * 4. 삭제할 id 받고, 삭제후 성공 반환
	 */
	public int deleteBakery(int id) throws Exception {
		
		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();){

			String sql = "delete from bakery where id = " + id;
			int result = stat.executeUpdate(sql);
			
			return result;
		}
		
	}
	
	/**
	 * 5-1. 수정할 id가 목록에 있는지 확인
	 */
	public boolean isIdExist(int id) throws Exception {
		
		String sql = "select * from bakery where id = "+ id;
		
		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				){
		
			boolean result = rs.next();
			return result;
		}
	}
	
	/**
	 * 5-2. 수정할 id 받고, 이름&가격 받고 수정 후 성공 반환
	 */
	public int updateBakery(int id, String name, int price) throws Exception {
		
		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();){
			
			String sql = "update bakery set name = '"+ name +"', price = "+ price +" where id = "+ id;
			
			int result = stat.executeUpdate(sql);
			
			return result;
			
		}
	}
	
	
	
	
	
	
	
}
