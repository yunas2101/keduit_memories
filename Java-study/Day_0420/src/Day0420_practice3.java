import java.util.Arrays;


public class Day0420_practice3 {

	public static void main(String[] args) {

		/** 프로그래머스 Lv.0 배열 두배 만들기 문제 **/
		int[] numbers = {1,2,3,4,5};
		int[] answer = new int[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			answer[i] = numbers[i] * 2;
		}

		System.out.println(Arrays.toString(answer)); // 출력방식 어렵네;;


		/** for each 만드려다가 실패한 방식ㅎ **/	
//		for(int num : numbers) {
//			answer[num] = numbers[num] * 2;
//		}
//		
//		System.out.println(answer);

		

	}

}
