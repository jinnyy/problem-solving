/*
 * [백준][1753] 최단경로
 * 122600 KB	776 ms
 *
 * Dijkstra's shortest path algorithm
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Main {
	static final int INF = 3000001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		Vertex[] vertices = new Vertex[V+1];
		for(int i=1; i<=V; i++) {
			vertices[i] = new Vertex();
		}
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			vertices[from].add(new Edge(vertices[to], cost));
		}
		br.close();
		
		
		// calculate
		for(int i=1; i<=V; i++) {
			vertices[i].dist = INF;
		}
		vertices[start].dist = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(vertices[start]);
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if(cur.visited) continue;
			cur.visited = true;
			
			for(Edge next : cur.adj) {
				next.to.dist = Math.min(next.to.dist, cur.dist + next.cost);
				pq.add(next.to);
			}
		}
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1; i<=V; i++) {
			if(vertices[i].dist >= INF) bw.append("INF");
			else bw.append(String.valueOf(vertices[i].dist));
			bw.newLine();
		}
		bw.close();
	}

}


class Vertex implements Comparable<Vertex> {
	static final int INF = 3000001;
	LinkedList<Edge> adj;
	boolean visited;
	int dist;
	
	Vertex() {
		this.adj = new LinkedList<Edge>();
		this.visited = false;
		this.dist = INF;
	}
	
	void add(Edge e) {
		this.adj.add(e);
	}
	
	@Override
	public int compareTo(Vertex v) {
		return this.dist - v.dist;
	}
	
}

class Edge {
	int cost;
	Vertex to;
	
	Edge(Vertex to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}

