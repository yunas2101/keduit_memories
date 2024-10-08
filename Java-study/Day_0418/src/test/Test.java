package test;

public class Test {

	public static void main(String[] args) {

		int index = 0;
//		int[] arr = new int[index++];
		int[] arr = new int[]{1,2,3,4};
		
		int result = 0;
		
		
		for (int i = 0; i < arr.length; i++) {
			result += arr[i];
		}
		double answer = result / arr.length;

		
		
		System.out.println(answer);
	}

}
