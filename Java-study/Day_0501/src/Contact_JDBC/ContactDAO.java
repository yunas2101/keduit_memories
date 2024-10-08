package Contact_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ContactDAO {

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
	 * 1. 정보 입력 받아서 DB에 저장 후 성공 반환
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int addContact(ContactDTO dto) throws Exception {
		String sql = "insert into contact values (contact_seq.nextval, ?,?,sysdate)";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){

			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPhone());

			int result = pstat.executeUpdate();
			return result;
		}

	}

	/**
	 * 2. DB의 데이터 출력
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ContactDTO> selectAll() throws Exception {

		String sql = "select * from contact";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){

			ArrayList<ContactDTO> list = new ArrayList<ContactDTO>();

			while(rs.next()) {

				int id = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				Timestamp reg_date = rs.getTimestamp(4);

				ContactDTO dto = new ContactDTO(id, name, phone, reg_date);
				list.add(dto);
			}

			return list;

		}
	}

	/**
	 * 3.검색할 이름 받아서 목록에 있다면 출력
	 * @param targetName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<ContactDTO> searchName(String targetName) throws Exception {

		String sql = "select * from contact where name like ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){

			pstat.setString(1, "%" + targetName + "%");

			try(ResultSet rs = pstat.executeQuery()){

				ArrayList<ContactDTO> list = new ArrayList<ContactDTO>();

				while(rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String phone = rs.getString(3);
					Timestamp reg_date = rs.getTimestamp(4);

					ContactDTO dto = new ContactDTO(id, name, phone, reg_date);
					list.add(dto);
				}
				return list;				
			}
		}
	}

	/**
	 * 4. 삭제할 대상의 id 받고 삭제 후 성공 반환
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteId(int id) throws Exception {

		String sql = "delete from contact where id = ?";	

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){

			pstat.setInt(1, id);

			int result = pstat.executeUpdate();

			return result;
		}
	}


	/**
	 * 4-1. 수정할 대상의 id 있는지 확인
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isIdExist(int id) throws Exception {

		String sql = "select * from contact where id = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){

			pstat.setInt(1, id);

			try(ResultSet rs = pstat.executeQuery()){

				boolean result =rs.next();
				return result;
			}
		}
	}


	/**
	 * 4-2. 수정할 id 받아서, 이름&번호 수정 후 성공 반환
	 * @param id
	 * @param name
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public int updateContact(int id, String name, String phone) throws Exception {

		String sql = "update contact set name = ?, phone = ? where id = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){

			pstat.setString(1, name);
			pstat.setString(2, phone);
			pstat.setInt(3, id);

			int result = pstat.executeUpdate();
			return result;

		}






	}
}
