
import java.util.ArrayList;
import java.util.Scanner;


public class Bakery_Main {

	public static int getValidNum() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요.");
				System.out.print(">> ");
			}
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		BakeryDAO dao = new BakeryDAO();

		while (true) {
			System.out.println("<< 베이커리 관리 시스템 >>");
			System.out.println("1. 신규 메뉴 등록");
			System.out.println("2. 메뉴 목록 출력");
			System.out.println("3. 메뉴 정보 검색 (name으로 검색)");
			System.out.println("4. 메뉴 삭제 (Id로 삭제)");
			System.out.println("5. 메뉴 수정");
			System.out.println("0. 시스템 종료");
			System.out.print(">> ");
			int menu = getValidNum();

			if (menu == 1) {
				System.out.print("ID : ");
				int id = Integer.parseInt(sc.nextLine());

				System.out.print("빵 이름 : ");
				String name = sc.nextLine();

				System.out.print("가격 : ");
				int price = getValidNum();

				BakeryDTO dto = new BakeryDTO(id, name, price);
				dao.addBakery(dto);

			} else if (menu == 2) {
				System.out.println("ID\t빵 이름\t가격");
				ArrayList<BakeryDTO> bakeryList = dao.getBakerys();
				if (bakeryList.size() == 0) {
					System.out.println("데이터가 없습니다.");
				} else {
					for (BakeryDTO bakery : bakeryList) {
						System.out.println(bakery.getId() + "\t" + bakery.getName() + "\t" + bakery.getPrice());
					}
				}

			} else if (menu == 3) {
				System.out.print("검색할 빵 이름 : ");
				String name = sc.nextLine();

				ArrayList<BakeryDTO> bakeryList = dao.searchBakery(name);

				if (bakeryList.size() == 0) {
					System.out.println("데이터가 없습니다.");
				} else {
					for (BakeryDTO bakery : bakeryList) {
						System.out.println(bakery.getId() + "\t" + bakery.getName() + "\t" + bakery.getPrice());
					}
				}

			} else if (menu == 4) {
				System.out.println("ID\t빵 이름\t가격");
				ArrayList<BakeryDTO> bakeryList = dao.getBakerys();
				for (BakeryDTO bakery : bakeryList) {
					System.out.println(bakery.getId() + "\t" + bakery.getName() + "\t" + bakery.getPrice());
				}

				System.out.print("삭제할 빵의 ID : ");
				int id = getValidNum();

				dao.deleteBakery(id);

			} else if (menu == 5) {
				System.out.println("ID\t빵 이름\t가격");
				ArrayList<BakeryDTO> bakeryList = dao.getBakerys();
				for (BakeryDTO bakery : bakeryList) {
					System.out.println(bakery.getId() + "\t" + bakery.getName() + "\t" + bakery.getPrice());
				}

				System.out.print("수정할 빵의 ID : ");
				int id = getValidNum();

				System.out.print("수정할 빵의 이름 : ");
				String name = sc.nextLine();

				System.out.print("수정할 빵의 가격 : ");
				int price = getValidNum();

				dao.updateBakery(id, name, price);

			} else if (menu == 0) {
				System.out.println("종료합니다.");
				System.exit(0);
			} else {
				System.out.println("재입력하세요.");
			}

		}
	}
}
