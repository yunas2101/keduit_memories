package day0426_MusicMVL;

import java.util.ArrayList;
import java.util.Scanner;

public class Music_Main {

	public static int getValidNum() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				return Integer.parseInt(sc.nextLine());
			}catch(Exception e) {
				System.out.println("숫자를 입력하세요.");
			}

		}
	}


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		MusicDAO dao = new MusicDAO();
		
		

		while(true) {
			System.out.println("<< 음악관리 시스템 >>");
			System.out.println("1. 신규 음악 등록");
			System.out.println("2. 음악 목록 출력");
			System.out.println("3. 음악 검색 ( 제목으로 검색 )");
			System.out.println("4. 음악 삭제 ( ID로 삭제 )");
			System.out.println("5. 음악 정보 변경 ( ID로 변경 )");
			System.out.println("0. 종료");
			System.out.print(">> ");
			int menu = getValidNum();


			if(menu == 1) {
				System.out.print("ID : ");
				String id = sc.nextLine();
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				
				System.out.print("가수 : ");
				String singer = sc.nextLine();
				
				MusicDTO dto = new MusicDTO(id, title, singer);
				dao.addMusic(dto);
				
				
			} else if(menu == 2) {
				
				System.out.println("ID\t제목\t가수");
				ArrayList<MusicDTO> musics = dao.getMusic();
				
				if(musics.size() == 0) {
					System.out.println("데이터가 없습니다.");
				} else {
					for(MusicDTO music : musics) {
						System.out.println(music.getId() + "\t" + music.getTitle() + "\t" + music.getSinger());
					}
				}
				
			} else if(menu == 3) {
				System.out.print("검색할 음악 제목 : ");
				String title = sc.nextLine();
				
				ArrayList<MusicDTO> musics = dao.searchMusic(title);
				if(musics.size() == 0) {
					System.out.println("데이터가 없습니다.");
				} else {
					for(MusicDTO music : musics) {
						System.out.println(music.getId() + "\t" + music.getTitle() + "\t" + music.getSinger());
					}
				}
				
			} else if(menu == 4) {
				System.out.println("삭제할 음악의 ID : ");
				
				ArrayList<MusicDTO> musics = dao.getMusic();
				System.out.println("ID\t제목\t가수");
				for(MusicDTO music : musics) {
					System.out.println(music.getId() + "\t" + music.getTitle() + "\t" + music.getSinger());
				}
				
				System.out.print(">> ");
				String id = sc.nextLine();
				
				dao.deleteMusic(id);
				
			} else if(menu == 5) {
				System.out.println("수정할 음악의 Id : ");
				
				ArrayList<MusicDTO> musics = dao.getMusic();
				System.out.println("ID\t제목\t가수");
				for(MusicDTO music : musics) {
					System.out.println(music.getId() + "\t" + music.getTitle() + "\t" + music.getSinger());
				}
				
				System.out.print(">> ");
				String id = sc.nextLine();
				
				
				System.out.print("수정할 음악의 제목 : ");
				String title = sc.nextLine();
				
				System.out.print("수정할 음악의 가수 : ");
				String singer = sc.nextLine();
				
				dao.updateMusic(id, title, singer);
				
				
			} else if(menu == 0) {
				System.out.println("종료합니다.");
				System.exit(0);

			} else {
				System.out.println("재입력하세요.");
			}



		}
	}
}
