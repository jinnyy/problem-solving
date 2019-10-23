/*
 * [프로그래머스][Level2] 라면 공장
 * https://programmers.co.kr/learn/courses/30/lessons/42629
 */
import java.util.PriorityQueue;
import java.util.Comparator;


class Solution {
	
	public int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		PriorityQueue<Integer> S = new PriorityQueue<Integer>(Comparator.reverseOrder());
		int date = 0;
		int idx = 0;
		while(date < k) {
			date += stock;
			if(date >= k) break;
			stock = 0;
			for(int i=idx; i<supplies.length; i++) {
				if(dates[i] > date) {
					idx = i;
					break;
				}
				S.add(supplies[i]);
				if(i == supplies.length - 1) idx = i+1;
			}
			stock = S.poll();
			answer++;
		}
		return answer;
	}
	
}

