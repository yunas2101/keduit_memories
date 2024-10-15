package day0426_MusicMVL;

import java.util.Scanner;

public class MusicDTO {

	// 멤머 필드
	private String id;
	private String title;
	private String singer;
	
	// 기본 생성자
	public MusicDTO() {
	}
	// 생성자
	public MusicDTO(String id, String title, String singer) {
		this.id = id;
		this.title = title;
		this.singer = singer;
	}
	
	// 멤버 메서드
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	
	
	
}
