package com.kedu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kedu.dto.MembersDTO;


@Repository
public class MembersDAO {
	
	
	@Autowired
	private JdbcTemplate jdbc;
	
	
	/**
	 * id 확인
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean isIdExist(String id) throws Exception {
		String sql = "select count(*) from members where id = ?";
//        try {
//             // 쿼리 결과가 존재하면 true를 반환
//             jdbc.queryForObject(sql, String.class, id);
//             return true;
//         } catch (EmptyResultDataAccessException e ) {
//             // 쿼리 결과가 없으면 false를 반환
//             return false;
//         }
		
		return jdbc.queryForObject(sql, Boolean.class, id);
		
	}
	
	/**
	 * 회원가입
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(MembersDTO dto) throws Exception {
		String sql = "insert into members values(?,?,?)";
		return jdbc.update(sql, dto.getId(), dto.getPw(), dto.getName());
	}
	
	
	/**
	 * id, pw 일치하는지 확인
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean isMember(MembersDTO dto) throws Exception {
		String sql = "select count(*) from members where id =? and pw=?";
//		try {
//            // 쿼리 결과가 존재하면 true를 반환
//            jdbc.queryForObject(sql, String.class , dto.getId(),dto.getPw());
//            return true;
//        } catch (EmptyResultDataAccessException e) {
//            // 쿼리 결과가 없으면 false를 반환
//            return false;
//        }
		
		return jdbc.queryForObject(sql, Boolean.class, dto.getId(), dto.getPw());
	}
	
	/**
	 * 회원 탈퇴
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteMember(String id) throws Exception {
		String sql = "delete from members where id=?";
		return jdbc.update(sql, id);
	}
	
	/**
	 * 마이페이지 정보 출력
	 * @param loginID
	 * @return
	 * @throws Exception
	 */
	public MembersDTO selectMember(String loginID) throws Exception {
		String sql = "select * from members where id = ?";
		return jdbc.queryForObject(sql,  new BeanPropertyRowMapper<>(MembersDTO.class), loginID);
	}
	
	
	/**
	 * 마이페이지 정보 수정
	 * @param name
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateMember(String name, String id) throws Exception {
		String sql = "update members set name=? where id=?";
		return jdbc.update(sql, name, id);
	}
	
	
	
	
	
// =================[ 예전 코드 ]=================
//	@Autowired
//	private BasicDataSource bds;
//	
//
//	/**
//	 * id 확인
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean isIdExist(String id) throws Exception {
//	
//		String sql = "select * from members where id =?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql)){
//			pstat.setString(1, id);
//			try(ResultSet rs = pstat.executeQuery()){
//				return rs.next();
//			}
//		}
//	}
//	
//	/**
//	 * 회원가입
//	 * @param dto
//	 * @return
//	 * @throws Exception
//	 */
//	public int insert(MembersDTO dto) throws Exception {
//		
//		String sql = "insert into members values(?,?,?)";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql)){
//			pstat.setString(1, dto.getId());
//			pstat.setString(2, dto.getPw());
//			pstat.setString(3, dto.getName());
//			return pstat.executeUpdate();
//			
//		}
//	}
//	
//	/**
//	 * id, pw 일치하는지 확인
//	 * @param dto
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean isMember(MembersDTO dto) throws Exception {
//		
//		String sql = "select * from members where id =? and pw=?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql)){
//			pstat.setString(1, dto.getId());
//			pstat.setString(2, dto.getPw());
//				
//			try (ResultSet rs = pstat.executeQuery();) {
//				return rs.next();
//			}
//		
//		}
//	}
//	
//	/**
//	 * 회원 탈퇴
//	 * @param dto
//	 * @return
//	 * @throws Exception
//	 */
//	public int deleteMember(String id) throws Exception {
//		String sql = "delete from members where id=?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql)){
//			pstat.setString(1, id);
//			return pstat.executeUpdate();
//		}
//		
//	}
//	
//	
//	/**
//	 * 마이페이지 정보 출력
//	 * @param loginID
//	 * @return
//	 * @throws Exception
//	 */
//	public MembersDTO selectMember(String loginID) throws Exception {
//
//		String sql = "select * from members where id = ?";
//
//		try (Connection con=bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql)) {
//
//			pstat.setString(1, loginID);
//
//			try (ResultSet rs = pstat.executeQuery()) {
//				while (rs.next()) {
//					String id = rs.getString("id");
//					String name = rs.getString("name");
//					
//					MembersDTO dto = new MembersDTO(id, null, name);
//					return dto;
//				}
//
//				return null;
//			}
//
//		}
//	}
//	
//	
//	/**
//	 * 마이페이지 정보 수정
//	 * @param name
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 */
//	public int updateMember(String name, String id) throws Exception {
//		String sql = "update members set name=? where id=?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql)){
//			pstat.setString(1, name);
//			pstat.setString(2, id);
//			return pstat.executeUpdate();
//		}
//		
//	}
	
	
}
	

