import java.util.ArrayList;
import java.util.Scanner;

public class MusicMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		MusicDAO dao = new MusicDAO();


		while(true) {
			try {
				System.out.println("<< 음악 관리 시스템 >>");
				System.out.println("1. 음악 등록");
				System.out.println("2. 음악 목록 출력");
				System.out.println("3. 음악 검색 (제목으로 검색)");
				System.out.println("4. 음악 삭제 (Id로 삭제)");
				System.out.println("5. 음악 수정");
				System.out.println("0. 시스템 종료");
				System.out.print(">> ");
				int menu = Integer.parseInt(sc.nextLine());

				if (menu == 1) {
					System.out.print("제목 : ");
					String title = sc.nextLine();
					
					System.out.print("가수 : ");
					String singer = sc.nextLine();

					MusicDTO dto = new MusicDTO(0, title, singer);
					int result = dao.addMusic(dto);
					if(result > 0) {
						System.out.println("입력 성공!");
					}
					
					
				} else if (menu == 2) {
					System.out.println("ID\t제목\t가수");
					
					ArrayList<MusicDTO> list = dao.selectAll();
					if(list.size() == 0) {
						System.out.println("데이터가 없습니다.");
					} else {
						for(MusicDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getTitle() + "\t" + dto.getSinger());
						}
					}
					

				} else if (menu == 3) {
					System.out.print("검색할 제목 : ");
					String title = sc.nextLine();
					
					ArrayList<MusicDTO> list = dao.searchTitle(title);
					if(list.size() == 0) {
						System.out.println("데이터가 없습니다.");
					} else {
						for(MusicDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getTitle() + "\t" + dto.getSinger());
						}
					}
					
				} else if (menu == 4) {
					System.out.print("삭제할 음악 Id : ");
					int id = Integer.parseInt(sc.nextLine());
					
					int result = dao.deleteMusic(id);
					if(result > 0) {
						System.out.println("삭제 성공!");
					}
					

				} else if (menu == 5) {
					System.out.print("수정할 음악 Id : ");
					int id = Integer.parseInt(sc.nextLine());
					
					boolean idExist = dao.isIdExist(id);
					if(idExist) {
						System.out.print("수정할 음악 제목 : ");
						String title = sc.nextLine();
						
						System.out.print("수정할 음악 가수 : ");
						String singer = sc.nextLine();
						
						int result = dao.updateMusic(id, title, singer);
						if(result > 0) {
							System.out.println("수정 성공!");
						}
					
					
					}



				} else if (menu == 0) {
					System.out.println("종료합니다.");
					System.exit(0);
				} else {
					System.out.println("재입력하세요.");
				}

			}catch(Exception e) {
				System.out.println("에러 발생 : " + e);
			}
		}
	}
}
