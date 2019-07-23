/*
 * [프로그래머스][DP] 서울에서 경산까지
 * https://programmers.co.kr/learn/courses/30/lessons/42899
 * 
 * dp로 풀지 못하고 queue로 모든 케이스를 탐색했을 때 시간초과가 발생했다.
 */
package programmers;
import java.util.LinkedList;


public class SeoulToKyungsan_v1 {

	public static int solution(int K, int[][] travel) {
		int N = travel.length;
		LinkedList<Node> q1 = new LinkedList<Node>();
		LinkedList<Node> q2 = new LinkedList<Node>();
		
		q1.add(new Node(travel[0][0], travel[0][1]));
		q1.add(new Node(travel[0][2], travel[0][3]));
		LinkedList<Node> curQ, nextQ=q2;
		int min = 0;
		for(int i=1; i<N; i++) {
			if(i%2==1) {curQ = q1; nextQ=q2; }
			else { curQ=q2; nextQ=q1; }
			while(!curQ.isEmpty()) {
				Node cur = curQ.poll();
				
				for(int j=0; j<2; j++) {
					int time = cur.time + travel[i][2*j];
					int money = cur.money + travel[i][2*j+1];
					if(i==N-1) {
						if(time <= K && money > min) {
							nextQ.add(new Node(time, money));
							min = money;
						}
					}
					else if(time <= K)
						nextQ.add(new Node(time, money));
				}
			}
		}
		int max = 0;
		while(!nextQ.isEmpty()) {
			max = Math.max(max, nextQ.poll().money);
		}
		return max;
	}
}

class Node {
	int money;
	int time;
	
	Node(int time, int money) {
		this.money = money;
		this.time = time;
	}
	
	public String toString() {
		return String.valueOf(this.money);
	}
}

