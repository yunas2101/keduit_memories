package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MembersDTO;

public class MembersDAO {

	/**
	 * DB 연결
	 * 
	 * @return
	 * @throws Exception
	 */
	// JNDI - Tomcat 서버에게 DBCP 인스턴스를 생성해 줄 것을 요구.
	private static MembersDAO instance;

	public synchronized static MembersDAO getInstance() {
		if (instance == null) {
			instance = new MembersDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	private MembersDAO() {
	}

	/**
	 * id 값 받고, DB의 id와 중복체크
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean idcheck(String id) throws Exception {

		String sql = "select * from members where id =?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, id);

			try (ResultSet rs = pstat.executeQuery()) {

				return rs.next();
			}

		}

	}

	/**
	 * DB에 값 넣기
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(MembersDTO dto) throws Exception {

		String sql = "insert into members values(?,?,?,?,?,?,?,?,sysdate)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getPhone());
			pstat.setString(5, dto.getEmail());
			pstat.setString(6, dto.getZipcode());
			pstat.setString(7, dto.getAddress1());
			pstat.setString(8, dto.getAddress2());

			int result = pstat.executeUpdate();
			return result;
		}
	}

	/**
	 * id, pw 받고 DB에 존재하는지
	 * 
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception
	 */
	public boolean login(String id, String pw) throws Exception {

		String sql = "select * from members where id = ? and pw = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, id);
			pstat.setString(2, pw);

			ResultSet rs = pstat.executeQuery();
			return rs.next();
		}
	}

	/**
	 * 회원 탈퇴 기능. DB에서 사용자 데이터 삭제
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(String loginID) throws Exception {

		String sql = "delete from members where id = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, loginID);

			int result = pstat.executeUpdate();

			return result;
		}

	}

	/**
	 * "내정보" 출력
	 * 
	 * @param loginID
	 * @return
	 * @throws Exception
	 */
	public MembersDTO selectMember(String loginID) throws Exception {

		String sql = "select * from members where id =?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, loginID);

			try (ResultSet rs = pstat.executeQuery()) {
				while (rs.next()) {
					String id = rs.getString(1);
					String name = rs.getString(3);
					String phone = rs.getString(4);
					String email = rs.getString(5);
					String zipcode = rs.getString(6);
					String add1 = rs.getString(7);
					String add2 = rs.getString(8);
					Timestamp join_date = rs.getTimestamp(9);
					MembersDTO dto = new MembersDTO(id, null, name, phone, email, zipcode, add1, add2, join_date);
					return dto;
				}

				return null;
			}

		}
	}
	
	/**
	 * 사용자가 수정한 내용 DB에서도 수정
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int edit(MembersDTO dto) throws Exception {
		
		String sql = "update members set name =?, phone= ?, email= ?, zipcode = ?, address1 =?, address2 =? where id = ?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getPhone());
			pstat.setString(3, dto.getEmail());
			pstat.setString(4, dto.getZipcode());
			pstat.setString(5, dto.getAddress1());
			pstat.setString(6, dto.getAddress2());
			pstat.setString(7, dto.getId());
			
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	
	
	
	

}
