/*
 * [프로그래머스][Level2] 숫자 야구
 * https://programmers.co.kr/learn/courses/30/lessons/42841?language=java
 */

public class NumberBaseball {
	
	public int solution(int[][] baseball) {
		int answer = 0;
		for(int first=1; first<=9; first++) {
			boolean[] visited = new boolean[10];
			visited[first] = true;
			for(int second=1; second<=9; second++) {
				if(visited[second]) continue;
				visited[second] = true;
				for(int third=1; third<=9; third++) {
					if(visited[third]) continue;
					visited[third] = true;
					int guess = first*100 + second*10 + third;
					if(isPossible(guess, baseball))
						answer++;
					visited[third] = false;
				}
				visited[second] = false;
			}
		}
		return answer;
	}
	
	
	private static boolean isPossible(int answer, int[][] baseballs) {
		for(int[] baseball : baseballs) {
			int strike = strike(answer, baseball[0]);
			int ball = ball(answer, baseball[0]) - strike;
			if(!(strike == baseball[1] && ball == baseball[2]))
				return false;
		}
		return true;
	}
	
	private static int strike(int answer, int guess) {
		int cnt = 0;
		for(int i=0; i<3; i++) {
			if(answer%10 == guess%10) cnt++;
			answer /= 10;
			guess /= 10;
		}
		return cnt;
	}
	
	private static int ball(int answer, int guess) {
		int cnt = 0;
		for(int i=0; i<3; i++) {
			int digit = answer % 10;
			int g = guess;
			for(int j=0; j<3; j++) {
				if(digit == g % 10) cnt++;
				g /= 10;
			}
			answer /= 10;
		}
		return cnt;
	}
}
