/*
 * [프로그래머스][Level3] 배달
 * https://programmers.co.kr/learn/courses/30/lessons/12978
 */
import java.util.LinkedList;


class Solution {
	static final int INF = Integer.MAX_VALUE;
	
	public int solution(int N, int[][] roads, int K) {
		int[] dist = new int[N+1];
		Vertex[] vertices = new Vertex[N+1];
		for(int i=1; i<=N; i++) {
			dist[i] = INF;
			vertices[i] = new Vertex();
		}
		for(int[] road : roads) {
			vertices[road[0]].add(road[1], road[2]);
			vertices[road[1]].add(road[0], road[2]);
		}
		boolean[] visited = new boolean[N+1];
		int cur = 1;
		dist[1] = 0;
		while(true) {
			if(visited[cur]) break;
			visited[cur] = true;
			
			for(Edge e : vertices[cur].adj) {
				dist[e.to] = Math.min(dist[e.to], dist[cur] + e.cost);
			}
			
			// select next
			int min = INF;
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				if(dist[i] < min) {
					min = dist[i];
					cur = i;
				}
			}
		}
		
		// count
		int answer = 0;
		for(int i=1; i<=N; i++) {
			if(dist[i] <= K) answer++;
		}
		return answer;
	}
}


class Vertex {
	LinkedList<Edge> adj;
	
	Vertex() {
		this.adj = new LinkedList<Edge>();
	}
	
	void add(int to, int cost) {
		this.adj.add(new Edge(to, cost));
	}
	
}

class Edge {
	int to, cost;
	
	Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}
