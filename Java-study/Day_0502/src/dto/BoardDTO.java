package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardDTO {

	private int seq;
	private String writer;
	private String contents;
	private Timestamp write_date;

	public BoardDTO() {
	}

	public BoardDTO(int seq, String writer, String contents, Timestamp write_date) {
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.write_date = write_date;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}

	// write_date를 문자열로 변환하여 반환하는 메서드 추가
	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(this.write_date);
	}

}
