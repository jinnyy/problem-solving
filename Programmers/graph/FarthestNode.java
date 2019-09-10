/*
 * [프로그래머스][level3] 가장 먼 노드
 */
import java.util.LinkedList;
import java.util.ArrayList;


public class FarthestNode {
	
	public int solution(int n, int[][] edge) {
		// generate graph
		ArrayList<LinkedList<Integer>> graph = new ArrayList<LinkedList<Integer>>();
		for(int i=0; i<=n; i++) {
			graph.add(new LinkedList<Integer>());
		}
		for(int[] E : edge) {
			graph.get(E[0]).add(E[1]);
			graph.get(E[1]).add(E[0]);
		}
		
		
		// bfs
		int max = 1;
		int[] distance = new int[n+1];
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.add(1);
		distance[1] = 1;
		
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			max = Math.max(max, distance[cur]);
			for(int next : graph.get(cur)) {
				if(distance[next] > 0) continue;
				distance[next] = distance[cur] + 1;
				Q.add(next);
			}
		}
		
		int cnt = 0;
		for(int i=1; i<=n; i++) {
			if(distance[i] == max) cnt++;
		}
		return cnt;
	}
}
