import java.util.Scanner;

public class Practice_multiplication {
	
	public static void main(String[] args) {
				
		// 1~9단의 프로그램 / 3단 고르면 3단의 내용 나오게 / 1~9단 이외는 범위초과

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("=== 구구단 출력 프로그램 ===");
			System.out.print("2~9 단 중 선택 : ");
			
			int dan = Integer.parseInt(sc.nextLine());

			if (dan >= 2 && dan <= 9) {
			
				for (int i = 1; i < 10; i++) {
					System.out.println(dan + " * " + i + " = " + (dan * i));
				}
			
			} else {
				System.out.println("범위를 벗어났습니다.");
			}
		}
				
	}
	

}

