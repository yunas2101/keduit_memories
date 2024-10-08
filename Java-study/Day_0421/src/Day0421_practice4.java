import java.util.Scanner;

public class Day0421_practice4 {
	public static void main(String[] args) {

		/** 1~9단의 프로그램 / 3단 고르면 3단의 내용 나오게 / 1~9단 이외는 범위초과 **/

		Scanner sc = new Scanner(System.in);
		
		
		while(true) {
		System.out.println("<< 구구단 출력기 >>");
		System.out.print("1~9단 중 선택하세요. : ");
		int dan = Integer.parseInt(sc.nextLine());
		
		if(dan < 10) {
			for (int i = 1; i < 10; i++) {
				System.out.println(dan + " * " + i + " = " + (dan*i));
			}
			System.out.println();
		}else {
			System.out.println("범위 초과입니다.");
			System.out.println();
		}
		
		}
	}
}
