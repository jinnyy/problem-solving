/*
 * [백준][4386] 별자리 만들기
 * 13776 KB	88 ms
 * (cf. 19-10-08 기준 java 5등)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;


public class Main {
	static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Star[] stars = new Star[N];
		parent = new int[N];
		for(int i=0; i<N; i++) {
			stars[i] = new Star(br.readLine());
			parent[i] = i;
		}
		br.close();
		
		// make edges
		PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == j) continue;
				minHeap.add(new Edge(i, j, Star.getCost(stars[i], stars[j])));
			}
		}
		
		// make mst
		float sum = 0;
		int cnt = 0;
		while(cnt < N-1) {
			Edge e = minHeap.poll();
			if(find(e.v1) != find(e.v2)) {
				union(e.v1, e.v2);
				cnt++;
				sum += Math.sqrt(e.cost);
			}
		}
		
		
		// write
		OutputStreamWriter ow = new OutputStreamWriter(System.out);
		ow.append(String.valueOf(sum));
		ow.close();
	}
	
	private static void union(int v1, int v2) {
		parent[find(v1)] = parent[find(v2)];
	}
	
	private static int find(int v) {
		if(v == parent[v]) return v;
		return parent[v] = find(parent[v]);
	}
	
	
}


class Star {
	float y, x;
	
	Star(String input){
		StringTokenizer st = new StringTokenizer(input, " ");
		this.x = Float.parseFloat(st.nextToken());
		this.y = Float.parseFloat(st.nextToken());
	}
	
	static float getCost(Star s1, Star s2) {
		float diffX = Math.abs(s1.x - s2.x);
		float diffY = Math.abs(s1.y - s2.y);
		return (diffX * diffX) + (diffY * diffY);
	}
}


class Edge implements Comparable<Edge>{
	int v1, v2;
	float cost;
	
	Edge(int v1, int v2, float cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge arg0) {
		return this.cost-arg0.cost>0 ? 1 : -1;
	}
	
}
