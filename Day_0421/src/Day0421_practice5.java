import java.util.Scanner;

public class Day0421_practice5 {
	public static void main(String[] args) {

		/** 메뉴 4개 보여주고, 잔액조회 누르면 현재 보유잔액 얼마인지,
			입금하기 누른 후 얼마 입금할건지 숫자 작성.
			출금하기 얼마.
			잔액부족
			종료하기 System.exit(0);
		 ** 코드 작성에 필요한 변수는 전부 최상단에 작성한다.** **/

		Scanner sc = new Scanner(System.in);

		int balance = 0;
		int dep = 0; // 입금
		int wd = 0; // 출금

		while(true) {

			System.out.println("<< ATM 시뮬레이터 >>");
			System.out.println("1.잔액조회");
			System.out.println("2.입금하기");
			System.out.println("3.출금하기");
			System.out.println("4.종료하기");
			System.out.print(">> ");
			int menu = Integer.parseInt(sc.nextLine());

			if (menu == 1) {
				System.out.println("현재 보유 잔액은 " + balance + "원 입니다.");
				System.out.println();

			} else if (menu == 2) {
				System.out.println("얼마를 입금하시겠습니까?");
				System.out.print(">> ");
				dep = Integer.parseInt(sc.nextLine());
				System.out.println(dep + "원이 입금되었습니다.");
				System.out.println();

				balance += dep;

			} else if (menu == 3) {
				System.out.println("얼마를 출금하시겠습니까?");
				System.out.print(">> ");
				wd = Integer.parseInt(sc.nextLine());
				
				if(balance < wd) {
					System.out.println("잔액이 부족합니다.");
					System.out.println("현재 잔액 : " + balance + "원");
					System.out.println();
					
				} else {
					System.out.println(wd + "원이 출금되었습니다.");
					balance -= wd;
					System.out.println("현재 잔액 : " + balance + "원");
					System.out.println();
				}

			} else if (menu == 4) {
				System.out.println("종료합니다.");
				System.exit(0);

			} else {
				System.out.println("재입력하세요.");
				System.out.println();
			} 

		}




	}
}
