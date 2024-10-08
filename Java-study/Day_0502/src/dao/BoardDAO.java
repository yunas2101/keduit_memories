package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import commons.Statics;
import dto.BoardDTO;

public class BoardDAO {

	/**
	 * DB연결
	 */
	public static BasicDataSource bds = new BasicDataSource(); // 모든 DAO가 하나의 bds를 사용하게 만들기 위해서 Static으로 전역변수 만들기

	public BoardDAO() { // 한번만 작동하면 되는 것들이라 생성자에 담아두는게 좋음
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("kedu01");
		bds.setPassword("kedu01");
		bds.setInitialSize(50); // 크게 만들수록 커넥션을 크게 만듦.
	}

	private Connection getConnection() throws Exception {
		return bds.getConnection();
	}

	/**
	 * 1. 글 작성하기
	 */
	public int addBoard(String writer, String contents) throws Exception {

		// String sql = "insert into board (contents) values (?)";
		String sql = "insert into board values (board_seq.nextval, ?, ?, sysdate)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, Statics.name);
			pstat.setString(2, contents);

			int result = pstat.executeUpdate();
			return result;
		}

	}

	/**
	 * 2.글 목록 보기
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<BoardDTO> selectList() throws Exception {

		String sql = "select * from board";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {

			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

			while (rs.next()) {
				int seq = rs.getInt(1);
				String writer = rs.getString(2);
				String contents = rs.getString(3);
				Timestamp write_date = rs.getTimestamp(4);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

				BoardDTO dto = new BoardDTO(seq, writer, contents, write_date);

				list.add(dto);
			}
			return list;
		}

	}

	/**
	 * 3. 글 검색하기
	 * 
	 * @param inputSearch
	 * @return
	 * @throws Exception
	 */
	public ArrayList<BoardDTO> searchBoard(String inputSearch) throws Exception {

		String sql = "select * from board where writer like ? OR contents like ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, "%" + inputSearch + "%");
			pstat.setString(2, "%" + inputSearch + "%");

			try (ResultSet rs = pstat.executeQuery()) {

				ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

				while (rs.next()) {
					int seq = rs.getInt(1);
					String writer = rs.getString(2);
					String contents = rs.getString(3);
					Timestamp write_date = rs.getTimestamp(4);

					BoardDTO dto = new BoardDTO(seq, writer, contents, write_date);
					list.add(dto);

				}
				return list;
			}

		}

	}

	/**
	 * 4-1. 수정할 글번호가 존재하는지 확인
	 */
	public boolean isSeqExist(int updateSeq) throws Exception {

		String sql = "select * from board where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, updateSeq);

			try (ResultSet rs = pstat.executeQuery();) {
				return rs.next();
			}

		}

	}

	/**
	 * 4-2. 수정할 글번호 받고, 작성자&내용&날짜 변경
	 * 
	 * @param seq
	 * @param writer
	 * @param contents
	 * @param write_date
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(int seq, String writer, String contents, String write_date) throws Exception {

		String sql = "update board set writer = ?, contents = ?, write_date = ? where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, writer);
			pstat.setString(2, contents);
			pstat.setString(3, write_date);
			pstat.setInt(4, seq);

			int result = pstat.executeUpdate();
			return result;

		}

	}

	/**
	 * 5. 삭제할 글 번호 받고, 삭제
	 * 
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(int seq) throws Exception {

		String sql = "delete from board where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			int result = pstat.executeUpdate();
			return result;

		}

	}

}
