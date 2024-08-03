package board.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private int seq;
	private String title;
	private String contents;
	private int count ;
	private String member_id;
	private Timestamp write_date;
	
	public BoardDTO() {
		super();
	}
	
	public BoardDTO(int seq, String title, String contents, int count, String member_id, Timestamp write_date) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.count = count;
		this.member_id = member_id;
		this.write_date = write_date;
	}


	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
}
