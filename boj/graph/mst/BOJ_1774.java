/*
 * [백준][1774] 우주신과의 교감
 * 37204 KB	208 ms
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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		God gods[] = new God[N];
		parent = new int[N];
		for(int i=0; i<N; i++) {
			gods[i] = new God(br.readLine());
			parent[i] = i;
		}
		int cnt = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if(union(from-1, to-1)) {
				cnt++;
			}
		}
		br.close();
		
		PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(find(i) == find(j)) continue;
				minHeap.add(new Edge(i, j, God.getCost(gods[i], gods[j])));
			}
		}
		
		double sum = 0;
		while(cnt < N-1) {
			Edge e = minHeap.poll();
			if(union(e.v1, e.v2)) {
				sum += e.cost;
				cnt++;
			}
		}
		OutputStreamWriter ow = new OutputStreamWriter(System.out);
		ow.append(String.format("%.2f", sum));
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
	double cost;
	
	Edge(int v1, int v2, double cost) {
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge e) {
		return this.cost < e.cost ? -1 : 1;
	}
}


class God {
	int x, y;
	
	God(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");
		this.x = Integer.parseInt(st.nextToken());
		this.y = Integer.parseInt(st.nextToken());
	}
	
	static double getCost(God g1, God g2) {
		long dx = g1.x - g2.x;
		long dy = g1.y - g2.y;
		return Math.sqrt((dx * dx) + (dy * dy));
	}
}
