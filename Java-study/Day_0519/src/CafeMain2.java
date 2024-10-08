import java.util.ArrayList;
import java.util.Scanner;

public class CafeMain2 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		CafeDAO2 dao = new CafeDAO2();

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
				String menu = sc.nextLine();

				if (menu.equals("1")) {
					System.out.print("이름 : ");
					String name = sc.nextLine();

					System.out.print("가격 : ");
					int price = Integer.parseInt(sc.nextLine());

					CafeDTO dto = new CafeDTO(0, name, price);

					int result = dao.addCafe(dto);
					if (result > 0) {
						System.out.println("입력 성공!");
					}

				} else if (menu.equals("2")) {

					ArrayList<CafeDTO> list = dao.selectAll();

					if (list.size() == 0) {
						System.out.println("데이터가 없습니다.");
					} else {
						System.out.println("ID\t이름\t가격");
						for (CafeDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getPrice());
						}
					}

				} else if (menu.equals("3")) {
					System.out.print("검색할 메뉴의 이름 : ");
					String name = sc.nextLine();

					ArrayList<CafeDTO> list = dao.searchMenu(name);

					if (list.size() == 0) {
						System.out.println("데이터가 없습니다.");
					} else {
						System.out.println("ID\t이름\t가격");
						for (CafeDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getPrice());
						}
					}

				} else if (menu.equals("4")) {
					System.out.print("삭제할 메뉴의 ID : ");
					int id = Integer.parseInt(sc.nextLine());
					
					int result = dao.deleteMenu(id);
					
					if(result >0) {
						System.out.println("삭제 성공!");
					}
					
				} else if (menu.equals("5")) {
					System.out.print("수정할 메뉴의 ID : ");
					int id = Integer.parseInt(sc.nextLine());
					
					boolean idExist = dao.isIdExist(id);
					
					if(idExist) {
						System.out.print("수정할 메뉴의 이름 : ");
						String name = sc.nextLine();
						
						System.out.print("수정할 메뉴의 가격 : ");
						int price = Integer.parseInt(sc.nextLine());
						
						CafeDTO dto = new CafeDTO(id,name,price);
						int result = dao.updateMenu(dto);
						
						
						if(result>0) {
							System.out.println("수정 성공!");
						}
						
						
					} else {
						System.out.println("ID가 존재하지 않습니다.");
					}
					
				} else if (menu.equals("0")) {
					System.out.println("종료합니다.");
					System.exit(0);
				} else {
					System.out.println("재입력하세요");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("관리자에게 문의하세요. admin@site.com");
			}
		}
		
	}
}
