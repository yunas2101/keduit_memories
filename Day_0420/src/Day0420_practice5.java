import java.util.Scanner;

public class Day0420_practice5 {

	public static void main(String[] args) {

		/** 국어, 수학, 영어 점수 입력받은 후 평균 출력
		 *	평균이 90 이상이면 A, 80 이상이면 B, 70 이상이면 C, 60 이상이면 D, 그 이하는 F **/

		Scanner sc = new Scanner(System.in);

		System.out.print("국어 점수 입력 : ");
		int kor = Integer.parseInt(sc.nextLine());

		System.out.print("영어 점수 입력 : ");
		int eng = Integer.parseInt(sc.nextLine());

		System.out.print("수학 점수 입력 : ");
		int math = Integer.parseInt(sc.nextLine());

		double avg = (kor + eng + math) / 3.0;

		if(avg >= 90) {
			System.out.println("평균 : " + avg + " / A 등급");
		} else if(avg >= 80) {
			System.out.println("평균 : " + avg + " / B 등급");
		} else if(avg >= 70) {
			System.out.println("평균 : " + avg + " / C 등급");
		} else if(avg >= 60) {
			System.out.println("평균 : " + avg + " / D 등급");
		} else {
			System.out.println("평균 : " + avg + " / F 등급");
		}

	}

}
