package programers;

public class Day0422_programers1 {

	public static void main(String[] args) {
		
		/** 프로그래머스 Lv.0 짝수 홀수 개수 문제 **/
		
		int[] num_list = {1,2,3,4,5,7};
		int[] answer = new int[2];

		int even = 0; // 짝수
		int odd = 0; // 홀수

		for (int i = 0; i < num_list.length; i++) {
			if(num_list[i] % 2 == 0) {
				even++; 
			} else if (num_list[i] % 2 == 1) {
				odd++; 
			}
		}
		answer[0] = even;
		answer[1] = odd;

		System.out.println(answer[0] + " , " + answer[1]);

	}
}
