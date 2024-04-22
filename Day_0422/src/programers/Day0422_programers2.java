package programers;

public class Day0422_programers2 {

	public static void main(String[] args) {

		/** 프로그래머스 Lv.0 점의 위치 구하기 문제 **/

		int[] dot = {-2,-4};
		int answer = 0;

		if (dot[0] >= 0 && dot[1] >= 0) {
			answer = 1;
		} else if (dot[0] < 0 && dot[1] >= 0) {
			answer = 2;
		} else if (dot[0] < 0 && dot[1] < 0) {
			answer = 3;
		} else {
			answer = 4;
		}
		
		System.out.println(answer);

		for (int i = 0; i < dot.length; i++) {
			if(dot[i] >= 0) {
				answer = 1;
			} else if(dot[i] < 0) {
				if(dot[0] < 0 && dot[1] >=0) {
					answer = 2;
				}
			}
		}

	}
}
