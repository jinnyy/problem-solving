/*
 * [백준][2887] 행성 터널
 */ 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;


public class Main {
	static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Vertex planet[] = new Vertex[N];
		
		for(int i=0; i<N; i++) {
			planet[i] = new Vertex(i, br.readLine());
		}
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int c=0; c<3; c++) {
			int k = c;
			Arrays.sort(planet, (a, b) -> Double.compare(a.loc[k], b.loc[k]));
			for(int i=1; i<N; i++) {
				int cost = Math.abs(planet[i-1].loc[c] - planet[i].loc[c]);
				pq.add(new Edge(planet[i-1].id, planet[i].id, cost));
			}
		}
		parent = new int[N];
		for(int i=1; i<N; i++) {
			parent[i] = i;
		}
		
		int cnt = 0;
		int sum = 0;
		while(cnt < N-1) {
			Edge e = pq.poll();
			if(union(e.v1, e.v2)) {
				cnt++;
				sum += e.cost;
			}
		}
		
		OutputStreamWriter ow = new OutputStreamWriter(System.out);
		ow.append(String.valueOf(sum));
		ow.close();
	}
	
	private static boolean union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if(p1 == p2) return false;
		parent[p1] = p2;
		return true;
	}
	
	private static int find(int v) {
		if(parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}
}

class Edge implements Comparable<Edge> {
	int v1, v2;
	int cost;
	
	Edge(int v1, int v2, int cost){
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge e) {
		return this.cost - e.cost;
	}
}

class Vertex {
	int id, loc[];
	
	Vertex(int id, String input) {
		this.id = id;
		this.loc = new int[3];
		StringTokenizer st = new StringTokenizer(input, " ");
		for(int i=0; i<3; i++)
			loc[i] = Integer.parseInt(st.nextToken());
	}
}
