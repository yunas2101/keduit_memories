import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MusicDAO {

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
	 * 1. 받은 음악 정보 "DB"에 저장 후 성공 반환
	 */
	public int addMusic(MusicDTO dto) throws Exception {

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement()){

			String sql = "insert into music values(music_seq.nextval, '"+ dto.getTitle() +"', '"+ dto.getSinger() +"')";
			int result = stat.executeUpdate(sql);

			return result;

		}
	}

	/**
	 * 2. 목록 출력
	 */
	public ArrayList<MusicDTO> selectAll() throws Exception {

		String sql = "select * from music";

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql);){

			ArrayList<MusicDTO> list = new ArrayList<MusicDTO>();

			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String singer = rs.getString("singer");

				MusicDTO dto = new MusicDTO(id, title, singer);
				list.add(dto);
			}

			return list;
		}
	}


	/**
	 * 3. 검색할 제목 받고, 포함된 제목 있으면 출력
	 */
	public ArrayList<MusicDTO> searchTitle(String getTitle) throws Exception {

		String sql = "select * from music where title like '%"+ getTitle +"%'";

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql);){

			ArrayList<MusicDTO> list = new ArrayList<MusicDTO>();

			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String singer = rs.getString("singer");

				MusicDTO dto = new MusicDTO(id, title, singer);
				list.add(dto);
			}
			return list;

		}

	}


	/**
	 * 4. 삭제할 id 받고, 삭제 후 성공 반환
	 */
	public int deleteMusic(int id) throws Exception {

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();){

			String sql = "delete from music where id = " + id;
			int result = stat.executeUpdate(sql);

			return result;
		}

	}



	/**
	 * 5-1. 수정할 대상의 id 있는지 확인
	 */
	public boolean isIdExist(int id) throws Exception {

		String sql = "select * from music where id = "+ id;

		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery(sql);){

			boolean result =rs.next();
			return result;
		}

	}


	/**
	 * 5-2. 수정할 id 받고, 제목&가수 받고 수정 후 성공 반환
	 */
	public int updateMusic(int id, String title, String singer) throws Exception {
		
		try(Connection con = this.getConnection();
				Statement stat = con.createStatement();){

			String sql = "update music set title = '"+ title +"', singer = '"+ singer +"' where id = " + id;
			int result = stat.executeUpdate(sql);

			return result;
		}
		
		
	}





}
