package day0426_MusicMVL;

import java.util.ArrayList;

public class MusicDAO {

	/**
	 * 배열에 저장하는 용도
	 */
	ArrayList<MusicDTO> musicList = new ArrayList<MusicDTO>();
	
	/**
	 * 사용자에게 받은 음악 정보 저장하는 용도
	 */
	public void addMusic(MusicDTO dto) {
		musicList.add(dto);
	}
	
	public ArrayList<MusicDTO> getMusic() {
		return musicList;
	}
	
	/**
	 * 검색할 음악 제목 받고, 목록에 있으면, 내용 출력
	 */
	public ArrayList<MusicDTO> searchMusic(String title){
		ArrayList<MusicDTO> result = new ArrayList<MusicDTO>();
		
		for(MusicDTO music : musicList){
			if(music.getTitle().contains(title)) {
				result.add(music);
			}
		}
		return result;
	}
	
	/**
	 * 삭제할 음악 id 받고, 목록에 동일한 id 있으면, 삭제
	 */
	public void deleteMusic(String id) {
		for(MusicDTO music : musicList) {
			if(music.getId().equals(id)) {
				musicList.remove(music);
				break;
			}
		}
	}
	
	/**
	 * 수정할 음악 id받고, 목록에 동일한 id 있으면, 제목&가수 수정
	 */
	public void updateMusic(String id, String title, String singer) {
		for(MusicDTO music : musicList) {
			if(music.getId().equals(id)) {
				music.setTitle(title);
				music.setSinger(singer);
				break;
			}
		}
	}
	
	
	
	
	
}
