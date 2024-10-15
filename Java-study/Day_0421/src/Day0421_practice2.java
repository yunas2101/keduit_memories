import java.util.Scanner;

public class Day0421_practice2 {
	public static void main(String[] args) {
		
		/** Hello를 사용자가 적는 숫자만큼 반복 출력 **/
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Hello를 몇 번 출력하시겠습니까? ");
		System.out.print(">> ");
		int num = Integer.parseInt(sc.nextLine());
		
		
		for (int i = 0; i < num; i++) {
			System.out.println("Hello");
		}
		
	}
}
