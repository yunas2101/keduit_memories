package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {

	/**
	 * DB 연결
	 * 
	 * @return
	 * @throws Exception
	 */
	// JNDI - Tomcat 서버에게 DBCP 인스턴스를 생성해 줄 것을 요구.
	private static BoardDAO instance;

	public synchronized static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}

	private BoardDAO() {
	}

	/**
	 * 작성된 리스트 출력
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BoardDTO> selectAll() throws Exception {

		String sql = "select * from board order by 1 desc";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {

			List<BoardDTO> list = new ArrayList<>();
			while (rs.next()) {
				int seq = rs.getInt("seq");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				Timestamp write_date = rs.getTimestamp("write_date");
				int view_count = rs.getInt("view_count");

				list.add(new BoardDTO(seq, writer, title, contents, write_date, view_count));
			}
			return list;
		}

	}

	/**
	 * n ~ m번에 해당되는 글 목록(+데이터) 꺼내기
	 * 
	 * @param startRecord
	 * @param endRecord
	 * @return
	 * @throws Exception
	 */
	public List<BoardDTO> selectNtoM(int startRecord, int endRecord) throws Exception {

		String sql = "select * from (select board.*, row_number() over(order by seq desc) as row_number from board) where row_number between ? and ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, startRecord);
			pstat.setInt(2, endRecord);

			try (ResultSet rs = pstat.executeQuery()) {
				List<BoardDTO> list = new ArrayList<>();
				while (rs.next()) {

					int seq = rs.getInt("seq");
					String writer = rs.getString("writer");
					String title = rs.getString("title");
					String contents = rs.getString("contents");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");

					list.add(new BoardDTO(seq, writer, title, contents, write_date, view_count));
				}
				return list;
			}
		}

	}

	/**
	 * 게시판 글 작성하기 위해 입력값 넣기
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insert(BoardDTO dto) throws Exception {

		String sql = "insert into board values(board_seq.nextval,?,?,?,sysdate,0)";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql, new String[] { "seq" });) {

			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getContents());
			pstat.executeUpdate();

			try (ResultSet rs = pstat.getGeneratedKeys()) {
				rs.next();
				int seq = rs.getInt(1);
				System.out.println(seq);
				return seq; // 입력해서 발생한 데이터의 seq 꺼내와라
			}
		}
	}

	/**
	 * 입력한 게시글 출력하기
	 * 
	 * @param target_seq
	 * @return
	 * @throws Exception
	 */
	public BoardDTO selectContent(int target_seq) throws Exception {

		String sql = "select * from board where seq =?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, target_seq);

			try (ResultSet rs = pstat.executeQuery()) {

				while (rs.next()) {

					int seq = rs.getInt("seq");
					String writer = rs.getString("writer");
					String title = rs.getString("title");
					String contents = rs.getString("contents");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");

					return new BoardDTO(seq, writer, title, contents, write_date, view_count);
				}
				return null;
			}

		}

	}

	/**
	 * 삭제할 seq 번호 받은 후, DB에서 삭제
	 * 
	 * @param target_seq
	 * @return
	 * @throws Exception
	 */
	public int delete(int target_seq) throws Exception {

		String sql = "delete from board where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, target_seq);

			int result = pstat.executeUpdate();

			return result;
		}
	}

	/**
	 * seq 값으로 제목, 내용 수정하기
	 * 
	 * @param target_seq
	 * @param title
	 * @param contents
	 * @return
	 * @throws Exception
	 */
	public int edit(int target_seq, String title, String contents) throws Exception {

		String sql = "update board set title = ?, contents = ? where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, title);
			pstat.setString(2, contents);
			pstat.setInt(3, target_seq);

			int result = pstat.executeUpdate();
			return result;
		}

	}

	/**
	 * 조회수 올리기
	 * 
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public int updateViewCount(int seq) throws Exception {

		String sql = "update board set view_count = view_count +1 where seq = ?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, seq);
			return pstat.executeUpdate();
		}

	}

	/**
	 * 전체 글의 갯수
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getRecordCount() throws Exception {

		String sql = "select count(*) from board";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {

			rs.next();
			return rs.getInt(1);
		}
	}

}
