package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp2.BasicDataSource;

import commons.Statics;
import dto.MembersDTO;

public class MembersDAO {

	/**
	 * DB연결
	 */
	public static BasicDataSource bds = new BasicDataSource(); // 모든 DAO가 하나의 bds를 사용하게 만들기 위해서 Static으로 전역변수 만들기

	public MembersDAO() { // 한번만 작동하면 되는 것들이라 생성자에 담아두는게 좋음
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kedu01");
		bds.setPassword("kedu01");
		bds.setInitialSize(50); // 크게 만들수록 커넥션을 크게 만듦.
	}

	private Connection getConnection() throws Exception {
		return bds.getConnection();
	}

	/**
	 * <로그인>한 사용자의 id,pw 받고 DB데이터와 비교하기
	 */
	public boolean loginMember(String id, String pw) throws Exception {

		String sql = "select * from members where id = ? And pw = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, id);
			pstat.setString(2, pw);

			try (ResultSet rs = pstat.executeQuery()) {

				boolean result = rs.next();
				if (result) {
					Statics.name = rs.getString("name");
				}
				return result;

			}

		}

	}

	/**
	 * <회원가입>한 사용자의 정보 받기
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int joinMember(MembersDTO dto) throws Exception {

		String sql = "insert into members values (?,?,?,sysdate)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());

			int result = pstat.executeUpdate();
			return result;
		}

	}

}
