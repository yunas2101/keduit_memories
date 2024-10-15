import java.util.Scanner;

public class Day0421_practice3 {
	public static void main(String[] args) {

		/** 숫자를 입력받아 홀짝을 구분하는 프로그램을 3번 반복 **/

		Scanner sc = new Scanner(System.in);
		int count = 0;

		while(count < 3) {
			System.out.print("1~100 사이의 수 입력 : ");
			int num = Integer.parseInt(sc.nextLine());

			System.out.println("=== 결 과 ===");

			if(num>=1 && num <= 100) {

				if(num % 2 == 0) {
					System.out.println("짝수입니다.");
				} else {
					System.out.println("홀수입니다.");
				}
			}else {
				System.out.println("범위를 초과하였습니다.");
			}

			count++;
		}
	}
}
