package Contact_JDBC;

import java.sql.Timestamp;

public class ContactDTO {

	private int id;
	private String name;
	private String phone;
	private Timestamp reg_date;
	
	public ContactDTO() {
	}

	public ContactDTO(int id, String name, String phone, Timestamp reg_date) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.reg_date = reg_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	
	
	
}
