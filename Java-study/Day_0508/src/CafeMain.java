import java.util.Scanner;

public class CafeMain {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		CafeDAO dao = new CafeDAO();
		
		while(true) {
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
			System.out.print("메뉴이름 : ");
			String name = sc.nextLine();
			
			System.out.print("가격 : ");
			int price = Integer.parseInt(sc.nextLine());
			
			CafeDTO dto = new CafeDTO(0, name, price);
			int result = dao.addCafe(dto);
			
			if (result > 0) {
				System.out.println("입력 성공!");
			}
			
		} else if (menu.equals("2")) {
			System.out.println("ID\t이름\t가격");
			
		} else if (menu.equals("3")) {
			
			
		} else if (menu.equals("4")) {
			
			
		} else if (menu.equals("5")) {
			
			
		} else if (menu.equals("0")) {
			System.out.println("종료합니다.");
			System.exit(0);
			
		} else {
			System.out.println("재입력하세요.");
		}
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("관리자에게 문의하세요. admin@site.com");
		}
		}
	}
}
