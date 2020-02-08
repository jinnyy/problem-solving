/*
 * [프로그래머스][Level2] 프린터
 * https://programmers.co.kr/learn/courses/30/lessons/42587
 */
 
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Comparator;


public class Solution {
	
	public int solution(int[] priorities, int location) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
		LinkedList<Doc> q = new LinkedList<Doc>();
		for(int loc=0; loc<priorities.length; loc++) {
			pq.add(priorities[loc]);
			q.add(new Doc(priorities[loc], loc));
		}
		
		int answer = 0;
		while(!q.isEmpty()) {
			Doc cur = q.poll();
			int max = pq.poll();
			
			if(cur.priority >= max) {
				answer++;
				if(cur.location == location) return answer;
				continue;
			}
			q.add(cur);
			pq.add(max);
		}
		
		return answer;
    }
	
	private class Doc implements Comparator<Doc> {
		int location;
		int priority;
		
		Doc(int priority, int location) {
			this.priority = priority;
			this.location = location;
		}

		@Override
		public int compare(Doc d1, Doc d2) {
			return d1.priority - d2.priority;
		}
	}
}

