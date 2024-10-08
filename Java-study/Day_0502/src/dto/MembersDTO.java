package dto;

import java.sql.Timestamp;

public class MembersDTO {

	private String id;
	private String pw;
	private String name;
	private Timestamp join_date;

	public MembersDTO() {
	}

	public MembersDTO(String id, String pw, String name, Timestamp join_date) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.join_date = join_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Timestamp join_date) {
		this.join_date = join_date;
	}

}
