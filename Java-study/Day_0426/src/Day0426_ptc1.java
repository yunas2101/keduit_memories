
public class Day0426_ptc1 {

	// 1.두개 정수를 인자로 전달받아 나눗셈한 결과를
	// 소수점 포함하여 반환하는 메서드 divide를 제작해주세요.

	public static double divide(int num1, int num2) {

		double result = (double) num1 / num2;

		return result;
	}

	// 2. 두개의 정수를 인자로 전달받아 더 큰 수를 반환하는 메서드 bigger를 제작해주세요.
	// 두 수가 같을 경우 0을 반환.

	// public static int bigger(int num1, int num2) {
	//
	// int result = 0;
	// if (num1 > num2) {
	// result = num1;
	// } else if (num1 < num2) {
	// result = num2;
	// } else {
	// result = 0;
	//
	// }
	//
	// return result;

	public static int bigger(int num1, int num2) {

		int result = 0;
		if (num1 > num2) {
			result = num1;
		} else if (num1 == num2) {
			result = 0;
		} else {
			result = num2;

		}

		return result;

	}

	// 3. 최소값과 최대값을 인자로 전달하면, 두 수 사이의 난수를 숫자로 반환하는 메서드
	// myRand
	public static int myRand(int min, int max) {
		int result = (int) (Math.random() * (max - min + 1) + min);
		return result;

	}

	public static void main(String[] args) {
		String str = "Ivaj:Jiva:Ivaj:Jalv:Iava:Ivaj:Jvaa:Jeva:Ivaj:Jeva:Jiva:Ivaj:Jiva:Iava:Ivaj:Jalv:Jvaa:Jalv:Java:Jiva:Iava:Jiva:Jova:Ivaj:Jiva:Ivaj:Jeva:Jvaa:Java:Jalv:Iava:Jiva:Ivaj:Java:Jvaa:Jalv:Java:Jova:Ivaj:Jova:Iava:Java:Jvaa:Jiva:Jeva:Jova:Jvaa:Jiva:Jvaa:Jeva:Jiva:Iava:Java:Java:Jvaa:Iava:Jova:Jeva:Ivaj:Jova:Iava:Jvaa:Jiva:Jalv:Java:Jvaa:Jiva:Jvaa:Jvaa:Ivaj:Jova:Jeva:Jalv:Java:Jalv:Ivaj:Ivaj:Java:Iava:Ivaj:Jeva:Ivaj:Jiva:Java:Iava:Java:Jiva:Jvaa:Jova:Java:Jiva:Jeva:Java:Ivaj:Jeva:Java:Jova:Ivaj:Ivaj:Jvaa:Iava:Jvaa:Jiva:Jeva:Iava:Jvaa:Jova:Jova:Jeva:Jeva:Jeva:Jalv:Ivaj:Jiva:Iava:Jova:Jova:Jeva:Jova:Jova:Java:Java:Jova:Ivaj:Jvaa:Jalv:Jalv:Jova:Java:Jeva:Ivaj:Jalv:Iava:Jiva:Java:Iava:Jiva:Java:Iava:Jalv:Jvaa:Iava:Jiva:Jalv:Jvaa:Jova:Jalv:Ivaj:Jeva:Jova:Jalv:Java:Ivaj:Jeva:Java:Jeva:Jova:Java:Jiva:Jova:Jvaa:Ivaj:Jalv:Jova:Jvaa:Jova:Jeva:Jeva:Jiva:Iava:Ivaj:Iava:Java:Java:Ivaj:Jiva:Java:Jeva:Jova:Jiva:Jalv:Jiva:Ivaj:Jova:Iava:Jiva:Jalv:Jiva:Java:Jova:Jalv:Iava:Jvaa:Jiva:Jvaa:Java:Ivaj:Jeva:Iava:Jvaa:Java:Jeva:Jeva:Ivaj:Jvaa:Java:Jvaa:Iava:Java:Jeva:Iava:Ivaj:Jiva:Jalv:Jvaa:Java:Jvaa:Jeva:Jalv:Jova:Jiva:Java:Java:Jiva:Jvaa:Iava:Jalv:Jiva:Jvaa:Jiva:Java:Jova:Java:Jeva:Jova:Jvaa:Jeva:Iava:Jeva:Jalv:Ivaj:Ivaj:Jova:Jvaa:Jova:Ivaj:Jova:Jova:Jova:Jeva:Ivaj:Java:Ivaj:Java:Jvaa:Jvaa:Jvaa:Jiva:Iava:Jova:Ivaj:Iava:Ivaj:Jiva:Jvaa:Jvaa:Java:Java:Jvaa:Iava:Iava:Jiva:Jalv:Jvaa:Java:Java:Java:Ivaj:Jeva:Jiva:Jova:Iava:Iava:Java:Java:Jova:Ivaj:Jalv:Jova:Jvaa:Jeva:Ivaj:Jiva:Jvaa:Jiva:Ivaj:Jiva:Iava:Iava:Jeva:Ivaj:Ivaj:Jiva:Ivaj:Jalv:Java:Jalv:Jova:Java:Ivaj:Ivaj:Ivaj:Jova:Jova:Iava:Java:Java:Jalv:Jalv:Jalv:Ivaj:Jvaa:Jalv:Jiva:Jiva:Java:Jalv:Ivaj:Iava:Ivaj:Ivaj:Jeva:Jiva:Jalv:Jvaa:Jeva:Jalv:Jvaa:Iava:Ivaj:Jiva:Jvaa:Jova:Jova:Ivaj:Iava:Ivaj:Jova:Jvaa:Iava:Iava:Ivaj:Iava:Jvaa:Jiva:Jeva:Java:Iava:Java:Jvaa:Jova:Jova:Ivaj:Jova:Iava:Ivaj:Jiva:Jiva:Jvaa:Jova:Jalv:Jalv:Jvaa:Jalv:Jeva:Iava:Jvaa:Ivaj:Jova:Jvaa:Ivaj:Java:Jalv:Java:Jeva:Java:Jeva:Jvaa:Iava:Jalv:Jiva:Iava:Jvaa:Jalv:Jalv:Jalv:Jvaa:Ivaj:Iava:Jeva:Jalv:Jvaa:Jeva:Iava:Iava:Java:Jvaa:Jalv:Iava:Ivaj:Jvaa:Jova:Jvaa:Jiva:Jova:Jalv:Jova:Jova:Java:Jeva:Jalv:Iava:Jiva:Jeva:Jeva:Java:Jiva:Jova:Jiva:Ivaj:Jova:Java:Jiva:Jalv:Ivaj:Jiva:Ivaj:Jeva:Jalv:Ivaj:Ivaj:Ivaj:Jvaa:Jalv:Ivaj:Jvaa:Jova:Iava:Ivaj:Jeva:Jalv:Jvaa:Iava:Jalv:Jeva:Jeva:Jvaa:Java:Jeva:Ivaj:Iava:Jiva:Ivaj:Java:Ivaj:Iava:Jeva:Jova:Jalv:Jiva:Jova:Jiva:Jalv:Jova:Jeva:Jiva:Ivaj:Jova:Java:Iava:Iava:Jiva:Ivaj:Jova:Iava:Jiva:Jova:Iava:Jalv:Jeva:Ivaj:Jova:Iava:Iava:Ivaj:Jvaa:Ivaj:Jalv:Jalv:Iava:Jalv:Jiva:Jalv:Ivaj:Jalv:Jova:Iava:Jova:Jova:Ivaj:Jvaa:Ivaj:Jeva:Jalv:Jeva:Jvaa:Jiva:Jalv:Jalv:Jvaa:Iava:Jvaa:Java:Ivaj:Jova:Iava:Jeva:Jeva:Ivaj:Jvaa:Iava:Iava:Ivaj:Jova:Jova:Jova:Jiva:Jeva:Ivaj:Jvaa:Ivaj:Iava:Jova:Iava:Jeva:Java:Jalv:Jvaa:Jiva:Jiva:Jeva:Java:Jeva:Java:Jova:Jova:Ivaj:Jiva:Iava:Jiva:Ivaj:Ivaj:Iava:Jeva:Jalv:Iava:Jova:Jalv:Jeva:Ivaj:Ivaj:Ivaj:Iava:Jalv:Iava:Ivaj:Jova:Ivaj:Java:Jeva:Jvaa:Jiva:Java:Jvaa:Jova:Jvaa:Jova:Jalv:Iava:Jvaa:Jeva:Java:Iava:Jiva:Java:Java:Java:Iava:Ivaj:Jova:Jeva:Jalv:Jova:Jvaa:Jvaa:Jeva:Ivaj:Iava:Iava:Java:Jvaa:Ivaj:Ivaj:Jeva:Jeva:Ivaj:Ivaj:Iava:Jeva:Java:Jova:Jvaa:Iava:Jvaa:Iava:Jvaa:Ivaj:Jova:Jalv:Jova:Java:Jeva:Jova:Iava:Jalv:Java:Jiva:Jalv:Iava:Iava:Iava:Jvaa:Jova:Jiva:Jiva:Jeva:Jeva:Jvaa:Jiva:Jova:Ivaj:Iava:Iava:Ivaj:Jiva:Ivaj:Ivaj:Jova:Jalv:Jeva:Java:Iava:Jalv:Jiva:Iava:Jeva:Jeva:Jiva:Jiva:Ivaj:Jalv:Jova:Jalv:Java:Java:Jalv:Jalv:Iava:Jova:Java:Jeva:Java:Jiva:Jvaa:Java:Iava:Jova:Ivaj:Jeva:Jalv:Jalv:Jvaa:Iava:Iava:Jova:Jvaa:Java:Jvaa:Jvaa:Jiva:Ivaj:Jvaa:Jvaa:Jova:Java:Jeva:Ivaj:Ivaj:Jalv:Java:Java:Jiva:Ivaj:Jalv:Java:Java:Jvaa:Jiva:Jeva:Jvaa:Java:Jova:Jvaa:Ivaj:Jalv:Java:Jiva:Jvaa:Jova:Java:Jeva:Jalv:Jalv:Ivaj:Java:Java:Iava:Jvaa:Iava:Java:Jeva:Jalv:Java:Jova:Java:Jvaa:Java:Jalv:Ivaj:Iava:Jova:Ivaj:Jalv:Ivaj:Jova:Ivaj:Java:Jvaa:Jvaa:Jova:Jvaa:Jalv:Jiva:Iava:Jalv:Java:Jalv:Java:Java:Java:Jeva:Jova:Jeva:Java:Ivaj:Jalv:Jova:Jeva:Jiva:Jvaa:Jova:Jiva:Iava:Jalv:Java:Jova:Jvaa:Jeva:Jiva:Jiva:Ivaj:Jeva:Jeva:Jeva:Java:Jalv:Jiva:Jalv:Jiva:Iava:Jeva:Java:Jeva:Ivaj:Ivaj:Jalv:Jeva:Jeva:Iava:Jvaa:Jiva:Jvaa:Jalv:Jvaa:Jiva:Jvaa:Java:Jova:Ivaj:Jeva:Java:Java:Jiva:Java:Ivaj:Jeva:Jvaa:Iava:Jiva:Iava:Jova:Jova:Jiva:Iava:Jova:Jova:Jova:Ivaj:Ivaj:Jvaa:Jalv:Iava:Jeva:Jvaa:Jiva:Java:Java:Jvaa:Jalv:Java:Iava:Iava:Jeva:Jalv:Jvaa:Java:Ivaj:Jiva:Jvaa:Iava:Jiva:Jova:Jalv:Jalv:Jova:Jova:Iava:Java:Jiva:Iava:Java:Jeva:Java:Jeva:Ivaj:Ivaj:Java:Jvaa:Jiva:Java:Jeva:Ivaj:Jova:Iava:Ivaj:Jalv:Java:Jova:Jvaa:Iava:Jova:Jova:Jalv:Java:Jeva:Jova:Jeva:Ivaj:Jeva:Jalv:Jeva:Jova:Java:Ivaj:Jiva:Ivaj:Java:Jalv:Jeva:Jeva:Jova:Jeva:Ivaj:Jvaa:Jvaa:Jova:Jalv:Jvaa:Jvaa:Java:Ivaj:Jalv:Java:Ivaj:Jiva:Iava:Jeva:Jova:Ivaj:Jeva:Jiva:Ivaj:Jalv:Jalv:Ivaj:Jova:Jvaa:Jeva:Java:Jiva:Jvaa:Jvaa:Java:Ivaj:Jalv:Jvaa:Jeva:Java:Java:Jeva:Java:Java:Java:Jeva:Java:Jiva:Jiva:Java:Jeva:Jalv:Ivaj:Iava:Java:Jalv:Java:Jiva:Iava:Jiva:Jiva:Jalv:Jalv:Jiva:Jalv:Jova:Jalv:Jiva:Jova:Jiva:Ivaj:Jalv:Java:Jiva:Ivaj:Ivaj:Jvaa:Jiva:Ivaj:Jova:Jvaa:Jeva:Java:Ivaj:Ivaj:Jeva:Jvaa:Jvaa:Jova:Jiva:Jeva:Ivaj";

		int count = countJava(str);
		System.out.println(count);

	}

	public static int countJava(String str) {
		int count = 0;

		String[] arr = str.split(":");

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].contains("Java")) {
				count++;
			}
		}

		return count;

	}
}
