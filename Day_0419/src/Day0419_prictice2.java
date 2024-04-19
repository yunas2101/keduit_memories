
public class Day0419_prictice2 {
	public int solution(int n) {

		/**프로그래머스 Lv.0 짝수의 합**/
		// for문에서 i는 n과 작거나 같을동안 반복을 하는데,
		// 만약 i 나누기 2 의 나머지값이 0 이라면, i의 값을 계속해서 더해야 한다.
		
		int answer = 0;
		for (int i = 0; i <=n; i++) {
			if((i % 2) == 0) {
				answer += i;
			}
		}
		return answer;

	}
}
