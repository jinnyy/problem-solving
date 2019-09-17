/*
 * [프로그래머스][level3] 단속카메라
 */
import java.util.PriorityQueue;


class Solution {
	
	public int solution(int[][] routes) {
		int cnt = 1;
		PriorityQueue<Route> R = new PriorityQueue<Route>();
		for(int[] route : routes) {
			R.add(new Route(route[0], route[1]));
		}
		
		Route prev = R.poll();
		int right = prev.right;
		while(!R.isEmpty()) {
			Route cur = R.poll();
			if(right > prev.right) right = prev.right;
			if(right < cur.left) {
				cnt++;
				right = cur.right;
			}
            prev = cur;
		}
		return cnt;
	}
}

class Route implements Comparable<Route> {
	int left, right;
	
	Route(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int compareTo(Route r) {
		return this.left - r.left;
	}
}
