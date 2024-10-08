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
	
	public List<BoardDTO> selectNtoM(int startRecord, int endRecord) throws Exception {
		
		String sql = "select * from (select board.*, row_number() over(order by seq desc) as row_number from board) where row_number between ? and ?";
	
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)) {
			
			
			pstat.setInt(1, startRecord);
			pstat.setInt(2, endRecord);
			
			try(ResultSet rs = pstat.executeQuery()) {
				List<BoardDTO> list = new ArrayList<>();
				while(rs.next()) {
					
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

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getContents());

			int result = pstat.executeUpdate();
			return result;
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
	
	private int getRecordCount() throws Exception {
		
		String sql = "select count(*) from board";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {
			
			rs.next();
			return rs.getInt(1);
		}
	}

	
	public String getPageNavi(int currentPage) throws Exception {

		// 1. 전체 글의 개수
		int recordTotalCount = this.getRecordCount(); // <-- 향후 DB에서 알아와야 하는 값
		
		// 2. 한 페이지에 몇 개의 게시글을 보여줄 것인지 결정
		int recordCountPerPage = 10;
		// 3. Page Navigator를 몇 개씩 보여줄 것인지 결정 (보통 10개)
		int naviCountPerPage = 10;
		
		
		// 작성된 글에 따라 보여져야 할 페이지 개수
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 현재 위치 
//		int currentPage = 7; // <-- 향후 클라이언트가 누르는 번호로 대체될 예정
		
		// 네비게이터 시작 값 
		int startNavi = (currentPage - 1) / recordCountPerPage * recordCountPerPage + 1;
		// 네비게이터 마지막 값
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needNext = true;
		boolean needPrev = true;
		
		if(startNavi == 1) {needPrev = false;}
		if(endNavi == pageTotalCount) {needNext = false;}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {sb.append("<a href='/list.board?cpage="+(startNavi-1)+"'>< </a>");}
		
		for(int i = startNavi; i<=endNavi; i++) {
			sb.append("<a href='/list.board?cpage=" + i + "'>" + i + "</a> ");
		}
		
		if(needNext) {sb.append("<a href='/list.board?cpage="+(endNavi+1)+"'>></a>");}
		return sb.toString();
		

	}
	
	
	
	
	
	
	
	

}
