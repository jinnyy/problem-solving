/*
 * [프로그래머스][Level2] 주식가격
 * https://programmers.co.kr/learn/courses/30/lessons/42584
 */
class Solution {
    public int[] solution(int[] prices) {
		int N = prices.length;
		int[] answer = new int[N];
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(prices[j] < prices[i]) {
					answer[i] = j - i;
					break;
				}
			}
			if(answer[i] == 0) answer[i] = N-1 - i;
		}
		return answer;
	}
}
