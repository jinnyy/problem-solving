/*
 * [프로그래머스][level3] 섬 연결하기
 * kruskal's_algorithm, union-find
 */
import java.util.PriorityQueue;


public class ConnectIslands {
	static int parent[];
	
	public int solution(int n, int[][] costs) {
		parent = new int[n];
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int[] cost : costs) {
			pq.add(new Edge(cost));
		}
		int cnt = 1;
		int answer = 0;
		while(cnt < n) {
			Edge cur = pq.poll();
			int v1 = cur.v[0];
			int v2 = cur.v[1];
			if(find(v1) == find(v2)) continue;
			answer += cur.cost;
			cnt++;
			union(v1, v2);
		}
		return answer;
	}
	
	private static void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		parent[p1] = p2;
	}
	
	private static int find(int v) {
		while(parent[v] != v) {
			v = parent[v];
		}
		return v;
	}
}


class Edge implements Comparable<Edge> {
	int v[], cost;
	
	Edge(int c[]) {
		this.v = new int[] {c[0], c[1]};
		this.cost = c[2];
	}
	
	@Override
	public int compareTo(Edge e) {
		return this.cost - e.cost;
	}
}
