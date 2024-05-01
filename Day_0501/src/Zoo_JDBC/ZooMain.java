package Zoo_JDBC;

import java.util.ArrayList;
import java.util.Scanner;

public class ZooMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ZooDAO dao = new ZooDAO();

		while(true) {
		try {
		System.out.println("<< 동물 관리 시스템 >>");
		System.out.println("1. 신규 동물 등록");
		System.out.println("2. 동물 목록 출력");
		System.out.println("3. 동물 정보 검색 (name으로 검색)");
		System.out.println("4. 동물 삭제 (Id로 삭제)");
		System.out.println("5. 동물 수정");
		System.out.println("0. 시스템 종료");
		System.out.print(">> ");
		String menu = sc.nextLine();


		if(menu.equals("1")) {
			System.out.print("이름 : ");
			String name = sc.nextLine();
			
			System.out.print("색깔 : ");
			String color = sc.nextLine();
			
			ZooDTO dto = new ZooDTO(0,name,color);
			int result = dao.addZoo(dto);
			
			if(result > 0) {
				System.out.println("입력 성공!");
			}
			
		} else if (menu.equals("2")) {
			
			ArrayList<ZooDTO> list = dao.seletAll();
			
			if(list.size() == 0) {
				System.out.println("데이터가 없습니다.");
				
			} else {
				System.out.println("ID\t이름\t색깔");
				for(ZooDTO dto : list) {
					System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getColor());
				}
			}
			
		} else if (menu.equals("3")) {
			System.out.print("검색할 동물 name : ");
			String name = sc.nextLine();
			
			ArrayList<ZooDTO> list = dao.searchName(name);
			
			if(list.size()==0) {
				System.out.println("데이터가 없습니다.");
			}else {
				System.out.println("ID\t이름\t색깔");
				for(ZooDTO dto : list) {
					System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getColor());
				}
			}
			
		} else if (menu.equals("4")) {
			System.out.print("삭제할 대상의 ID : ");
			int id = Integer.parseInt(sc.nextLine());
			
			int result = dao.deleteZoo(id);
			if(result > 0) {
				System.out.println("삭제 성공!");
			}
			
		} else if (menu.equals("5")) {
			System.out.print("수정할 대상의 ID : ");
			int id = Integer.parseInt(sc.nextLine());
			
			boolean idExist = dao.isIdExist(id);
			if(idExist) {
				System.out.print("수정할 대상의 이름 : ");
				String name = sc.nextLine();
				
				System.out.print("수정할 대상의 색깔 : ");
				String color = sc.nextLine();
				
				int result = dao.updateZoo(id, name, color);
				if(result > 0) {
					System.out.println("수정 성공!");
				}

			}else {
				System.out.println("대상이 없습니다.");
			}
			
		} else if (menu.equals("0")) {
			System.out.println("종료합니다.");
			System.exit(0);
		} else {
			System.out.println("재입력하세요.");
		}

		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("관리자에게 문의하세요. admin@site.com");
		}
		}
	}
}
