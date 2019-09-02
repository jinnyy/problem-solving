/*
 * [프로그래머스][level3] 이중우선순위큐
 * https://programmers.co.kr/learn/courses/30/lessons/42628
 */
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Collections;


class Solution {
	public int[] solution(String[] operations) {
		PriorityQueue<Number> minHeap = new PriorityQueue<Number>();
		PriorityQueue<Number> maxHeap = new PriorityQueue<Number>(operations.length, Collections.reverseOrder());
		int idx = 0;
		boolean[] visited = new boolean[operations.length+1];
		// 인덱스를 이용해 (다른 heap에서) 이미 pop한 객체라면 새롭게 pop할 때 무시한다.
		for(String op : operations) {
			StringTokenizer st = new StringTokenizer(op, " ");
			if(st.nextToken().equals("I")) {
				int num = Integer.parseInt(st.nextToken());
				minHeap.add(new Number(num, idx));
				maxHeap.add(new Number(num, idx));
				idx++;
			} else {
				String type = st.nextToken();
				PriorityQueue<Number> pq;
				if(type.equals("1")) pq = maxHeap;
				else pq = minHeap;
				if(getVal(pq, visited)==0) continue;
			}
			
		}
		return new int[] {getVal(maxHeap, visited), getVal(minHeap, visited)};
	}
	
	private static int getVal(PriorityQueue<Number> pq, boolean[] visited) {
		Number target;
		while(true) {
			if(pq.isEmpty()) return 0;
			target = pq.poll();
			if(!visited[target.idx]) break;
		}
		visited[target.idx] = true;
		return target.val;
	}
}

class Number implements Comparable<Number> {
	int val, idx;
	
	Number(int val, int idx) {
		this.val = val;
		this.idx = idx;
	}
	
	@Override
	public int compareTo(Number n) {
		return this.val - n.val;
	}
}
