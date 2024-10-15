
public class Day0420_practice1 {

	public static void main(String[] args) {

		/** 프로그래머스 Lv.0 중복된 숫자 개수 문제 **/
		
		int[] arr = new int[] {1,2,2,2,4,5};
		int answer = 0;
		int n = 2;

		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == n) {
				answer++;
			}
		}
		System.out.println(answer);

	}

}
