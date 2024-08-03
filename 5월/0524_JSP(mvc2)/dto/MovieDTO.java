package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MovieDTO {
	
	private int id;
	private String title;
	private String genre;
	private Timestamp movie_date;
	
	public MovieDTO() {
	}

	public MovieDTO(int id, String title, String genre, Timestamp movie_date) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.movie_date = movie_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Timestamp getMovie_date() {
		return movie_date;
	}

	public void setMovie_date(Timestamp movie_date) {
		this.movie_date = movie_date;
	}


	
	
}
