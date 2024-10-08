import java.util.Scanner;

public class Day0426_ptc2 {

	public static int validInput(String ui) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			try {
				System.out.print(ui); // ui 보여줘야 밑의 return 값인 숫자 입력할 수 있음
				return Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자만 입력하세요.");
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num1 = 0;
		int num2 = 0;

		while (true) {

			System.out.println("=== 계산기 프로그램 ===");
			System.out.print("연산자 입력 (+,-,*,/) : ");

			String input = sc.nextLine();
			if (input.equals("q")) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}

			num1 = validInput("첫 번째 숫자 입력 : ");
			num2 = validInput("두 번째 숫자 입력 : ");

			System.out.println("====== 결 과 =======");

			if (input.equals("+")) {
				int plus = num1 + num2;
				System.out.println(num1 + " " + input + " " + num2 + " = " + plus);
			} else if (input.equals("-")) {
				int minus = num1 - num2;
				System.out.println(num1 + " " + input + " " + num2 + " = " + minus);
			} else if (input.equals("*")) {
				int multi = num1 * num2;
				System.out.println(num1 + " " + input + " " + num2 + " = " + multi);
			} else if (input.equals("/")) {
				int div = num1 / num2;
				System.out.println(num1 + " " + input + " " + num2 + " = " + div);
			} else {
				System.out.println("다시 입력해주세요.");
			}
		}
	}

}
