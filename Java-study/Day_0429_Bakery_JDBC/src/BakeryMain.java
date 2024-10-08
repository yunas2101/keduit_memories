

import java.util.ArrayList;
import java.util.Scanner;

public class BakeryMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		BakeryDAO dao = new BakeryDAO();

		while (true) {
			try {
				System.out.println("<< 카페 메뉴관리 시스템 >>");
				System.out.println("1. 신규 메뉴 등록");
				System.out.println("2. 메뉴 목록 출력");
				System.out.println("3. 메뉴 정보 검색 (name으로 검색)");
				System.out.println("4. 메뉴 삭제 (Id로 삭제)");
				System.out.println("5. 메뉴 수정");
				System.out.println("0. 시스템 종료");
				System.out.print(">> ");
				int menu = Integer.parseInt(sc.nextLine());

				if (menu == 1) {
					System.out.print("이름 : ");
					String name = sc.nextLine();

					System.out.print("가격 : ");
					int price = Integer.parseInt(sc.nextLine());

					BakeryDTO dto = new BakeryDTO(0, name, price);
					int result = dao.addBakery(dto);
					if(result > 0) {
						System.out.println("입력 성공!");
					}

				} else if (menu == 2) {
					ArrayList<BakeryDTO> list = dao.selectAll();

					if(list.size() == 0) {
						System.out.println("데이터가 없습니다.");
					} else {
						System.out.println("Id\t이름\t가격");
						for(BakeryDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getPrice());
						}
					}

				} else if (menu == 3) {
					System.out.print("검색할 이름 : ");
					String name = sc.nextLine();

					ArrayList<BakeryDTO> list = dao.searchName(name);

					if(list.size() == 0) {
						System.out.println("데이터가 없습니다.");
					} else {
						System.out.println("Id\t이름\t가격");
						for(BakeryDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getPrice());
						}
					}

				} else if (menu == 4) {
					System.out.print("삭제할 ID : ");
					int id = Integer.parseInt(sc.nextLine());

					int result = dao.deleteBakery(id);
					if(result > 0) {
						System.out.println("삭제 성공!");
					}
					
				} else if (menu == 5) {
					System.out.print("수정할 Id : ");
					int id = Integer.parseInt(sc.nextLine());
					
					boolean idExist = dao.isIdExist(id);
					
					if(idExist) {
						System.out.print("수정할 이름 : ");
						String name = sc.nextLine();
						
						System.out.print("수정할 가격 : ");
						int price = Integer.parseInt(sc.nextLine());
					
						int result = dao.updateBakery(id, name, price);
						if(result>0) {
							System.out.println("수정 성공!");
						}
						
					}
					
					
					
					
				} else if (menu == 0) {
					System.out.println("종료합니다.");
					System.exit(0);
				} else {
					System.out.println("재입력하세요.");
				}

			} catch(Exception e) {
				System.out.println("에러발생 : " + e);
			}
		}

	}
}
