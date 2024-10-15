
public class Day0420_practice2 {

	public static void main(String[] args) {

		/** 프로그래머스 Lv.0 머쓱이보다 키 큰 사람 문제 **/
		int[] array = new int[] { 149, 180, 192, 170 };
		int height = 149;
		int answer = 0;
		
		
//		for (int i = 0; i < array.length; i++) {
//			if(array[i] > height) {
//				answer++;
//			}
//		}
		

		for(int num : array) {
			if(num > height) {
				answer++;
			}
		}

		System.out.println(answer);
		
	}

}
