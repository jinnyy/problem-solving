/*
 * [백준][1197] 최소 스패닝 트리
 * 	55768 KB	540 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;


public class Main {
	static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();
		for(int i=0; i<E; i++) {
			minHeap.add(new Edge(br.readLine()));
		}
		br.close();
		
		parent = new int[V+1];
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
		int cnt = 1;
		long sum = 0;
		while(cnt < V) {
			Edge min = minHeap.poll();
			if(find(min.v1) != find(min.v2)) {
				union(min.v1, min.v2);
				cnt++;
				sum += min.cost;
			}
			
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(String.valueOf(sum));
		bw.close();
	}
	
	static void union(int v1, int v2) {
		parent[find(v1)] = find(v2);
	}
	
	static int find(int v) {
		if(v == parent[v]) return v;
		return parent[v] = find(parent[v]);
	}

}

class Edge implements Comparable<Edge> {
	int v1, v2, cost;
	
	Edge(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");
		this.v1 = Integer.parseInt(st.nextToken());
		this.v2 = Integer.parseInt(st.nextToken());
		this.cost = Integer.parseInt(st.nextToken());
	}
	Edge(int v1, int v2, int cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge e) {
		return this.cost - e.cost;
	}
}
