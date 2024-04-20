import java.util.Scanner;

public class Day0420_practice4 {

	public static void main(String[] args) {

		/** 1~100까지의 숫자 중 입력한 숫자가 홀수인지 짝수인지 출력 **/

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.print("1~100 사이의 수 입력 : ");
			int num = Integer.parseInt(sc.nextLine());

			System.out.println("=== 결 과 ===");
			
			if(num >= 1 && num <= 100) {
				if(num % 2 == 1) {
					System.out.println("홀수입니다.");
					System.out.println();
				} else {
					System.out.println("짝수입니다.");
					System.out.println();
				} 
			} else {
				System.out.println("범위 초과입니다.");
				System.out.println();
			}

		}
	}

}
