import java.util.Arrays;

public class day0423_programmers1 {

	/** 프로그래머스 Lv.0 중앙값 구하기 문제**/
	//	class Solution {
	//		public int solution(int[] array) {

	//			Arrays.sort(array);
	
	//			int answer = array[array.length / 2];
	
	//			return answer;
	//
	//		}

	//	}


	public static void main(String[] args) {

		int[] arr = {1,2,7,10,11};

		Arrays.sort(arr);
		int answer = arr[arr.length / 2];

		System.out.println(answer);
	}
}
