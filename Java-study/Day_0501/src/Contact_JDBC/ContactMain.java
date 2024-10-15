package Contact_JDBC;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ContactDAO dao = new ContactDAO();

		while(true) {
			try {
				System.out.println("<< 연락처 관리 시스템 >>");
				System.out.println("1. 연락처 등록");
				System.out.println("2. 목록 출력");
				System.out.println("3. 정보 검색 (name으로 검색)");
				System.out.println("4. 연락처 삭제 (Id로 삭제)");
				System.out.println("5. 연락처 수정 (Id로 삭제)");
				System.out.println("0. 시스템 종료");
				System.out.print(">> ");
				String menu = sc.nextLine();


				if(menu.equals("1")) {
					System.out.print("이름 : ");
					String name = sc.nextLine();

					System.out.print("전화번호 : ");
					String phone = sc.nextLine();			

					ContactDTO dto = new ContactDTO(0, name, phone, null);

					int result = dao.addContact(dto);
					if(result > 0) {
						System.out.println("입력 성공!");
					}
					

				}else if (menu.equals("2")) {
					System.out.println("ID\t이름\t번호\t\t날짜");
					
					ArrayList<ContactDTO> list = dao.selectAll();
					
					if(list.size()==0) {
						System.out.println("데이터가 없습니다.");
					} else {
						for(ContactDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getPhone() + "\t" + dto.getReg_date());
						}
					}
					
				}else if (menu.equals("3")) {
					System.out.print("검색할 대상의 이름 : ");
					String name = sc.nextLine();
					
					ArrayList<ContactDTO> list = dao.searchName(name);
					if(list.size()==0) {
						System.out.println("데이터가 없습니다.");
					}else {
						for(ContactDTO dto : list) {
							System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getPhone() + "\t" + dto.getReg_date());
						}
					}

				}else if (menu.equals("4")) {
					System.out.print("삭제할 대상의 ID : ");
					int id = Integer.parseInt(sc.nextLine());
					
					int result = dao.deleteId(id);
					if(result > 0) {
						System.out.println("삭제 성공!");
					}

				}else if (menu.equals("5")) {
					System.out.print("수정할 대상의 ID : ");
					int id = Integer.parseInt(sc.nextLine());
					
					boolean idExist = dao.isIdExist(id);
					
					if(idExist) {
						System.out.println("수정할 대상의 이름 : ");
						String name = sc.nextLine();
						
						System.out.println("수정할 대상의 번호 : ");
						String phone = sc.nextLine(); 
						
						int result = dao.updateContact(id, name, phone);
						if(result>0) {
							System.out.println("수정 성공!");
						}
					}
					
				}else if (menu.equals("0")) {
					System.out.println("종료합니다.");
					System.exit(0);
				}else {
					System.out.println("재입력하세요.");
				}






			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("관리자에게 문의하세요. admin@site.com");
			}

		}
	}
}
