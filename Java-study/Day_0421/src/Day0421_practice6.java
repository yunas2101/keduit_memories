import java.util.Scanner;

public class Day0421_practice6 {
	public static void main(String[] args) {

		/** 자판기 시뮬레이터 
		 *  조건1. 현재 잔액이 음료의 가격보다 작을 경우 계산 X
		 *  조건2. 구매한 음료의 개수가 + 될 수 있도록 **/

		Scanner sc = new Scanner(System.in);

		int money = 10000; // 기본 소지금
		int cokeprice = 1200;
		int ciderprice = 800;
		int teaprice = 1500;
		int coke = 0; 
		int cider = 0; 
		int tea = 0;


		while(true) {

			System.out.println("<< 자판기 시뮬레이터 >>");
			System.out.println("1.콜라(1200원) 2.사이다(800원) 3.매실차(1500원) 4.종료");
			System.out.print(">> ");
			int menu = Integer.parseInt(sc.nextLine());

			if (menu == 1) {
				if(money >= cokeprice) {
					System.out.println("콜라를 구매했습니다.");
					System.out.println("소지금 -" + cokeprice + "원");
					coke++;
					System.out.println("콜라 " + "+" + coke);
					money -= cokeprice;
					System.out.println("현재 잔액 : " + money);
					System.out.println();

				} else {
					System.out.println("잔액이 부족합니다.");
					System.out.println("현재 잔액 : " + money);
					System.out.println();
				}

			} else if (menu == 2) {

				if(money >= ciderprice) {
					System.out.println("사이다를 구매했습니다.");
					System.out.println("소지금 -" + ciderprice + "원");
					cider++;
					System.out.println("사이다 " + "+" + cider);
					money -= ciderprice;
					System.out.println("현재 잔액 : " + money);
					System.out.println();

				} else {
					System.out.println("잔액이 부족합니다.");
					System.out.println("현재 잔액 : " + money);
					System.out.println();
				}

			} else if (menu == 3) {

				if(money >= teaprice) {
					System.out.println("매실차를 구매했습니다.");
					System.out.println("소지금 -" + teaprice + "원");
					tea++;
					System.out.println("매실차 " + "+" + tea);
					money -= teaprice;
					System.out.println("현재 잔액 : " + money);
					System.out.println();
					
				} else {
					System.out.println("잔액이 부족합니다.");
					System.out.println("현재 잔액 : " + money);
					System.out.println();
				}

			} else if (menu == 4) {
				System.out.println("종료합니다.");
				System.exit(0);
			} else {
				System.out.println("메뉴를 다시 확인해주세요.");
				System.out.println();
			}

		}

	}
}
