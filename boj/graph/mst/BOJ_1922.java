/*
 * [백준][1922] 네트워크 연결
 * 54016 KB	380 ms
 * union-find (Kruskal's algorithm)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;


public class Main {
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
		for(int i=0; i<M; i++) {
			edges.add(new Edge(br.readLine()));
		}
		br.close();
		
		int cost = 0;
		parents = new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		int connected = 0;
		while(connected < N-1) {
			Edge e = edges.poll();
			if(find(e.v1) != find(e.v2)) {
				union(e.v1, e.v2);
				cost += e.cost;
				connected++;
			}
		}
		System.out.print(cost);
	}
	
	private static void union(int v1, int v2) {
		int parent = find(v1);
		int child = find(v2);
		parents[child] = parent;
	}
	
	private static int find(int v) {
		int p = v;
		while(parents[p] != p) {
			p = parents[p];
		}
		parents[v] = p;
		return p;
	}

}

class Edge implements Comparable<Edge> {
	int v1, v2, cost;
	
	Edge(String str){
		StringTokenizer st = new StringTokenizer(str, " ");
		this.v1 = Integer.parseInt(st.nextToken());
		this.v2 = Integer.parseInt(st.nextToken());
		this.cost = Integer.parseInt(st.nextToken());
	}
	
	@Override
	public int compareTo(Edge e) {
		return this.cost - e.cost;
	}
}
