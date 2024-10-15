
public class Day0707_programers2 {

	public int[] solution(int money) {
		
		/** 프로그래머스 Lv.0 아이스 아메리카노 **/
        int[] answer = new int[2];
        
        answer[0] = money / 5500;
        answer[1] = money % 5500;
        return answer;
    }
}
