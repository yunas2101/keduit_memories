
public class Day0421_practice1 {
	public static void main(String[] args) {
		
		/** 10의 배수마다 줄 바꾸기 **/
		/** ex) 1 2 3 4 5 6 7 8 9 10
		 * 		11 12 13 14 15 16 17 18 19 20
		 * 		~~                         100 **/

		int num = 1;

		while(num <= 100) {
			System.out.print(num + " ");

			if(num % 10 == 0) {
				System.out.println();
			}
			num++;
		}
		
	}
}
