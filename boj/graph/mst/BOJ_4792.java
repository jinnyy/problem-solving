/*
 * [백준][4792] 레드 블루 스패닝 트리
 * 219824 KB	736 ms
 * (19-10-12 14:06 기준 java 1위)
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static int N, K, parent[];
	static LinkedList<Edge> R, B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		while(!(N==0 && M==0 && K==0)) {
			R = new LinkedList<Edge>();
			B = new LinkedList<Edge>();
			for(int i=0; i<M; i++) {
				Edge e = new Edge(br.readLine());
				if(e.type) R.add(e);
				else B.add(e);
			}
			
			
			initialize();
			int min = getMin(R, B);
			initialize();
			int max = getMax(R, B);
			
			bw.append((min <= K && K <= max) ? "1" : "0");
			bw.newLine();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
		}
		br.close();
		bw.close();
	}
	
	static int getMin(LinkedList<Edge> R, LinkedList<Edge> B) {
		int blue = 0;
		int cnt = 0;
		for(Edge r : R) {
			if(union(r.v1, r.v2)) cnt++;
			if(cnt >= N-1) return blue;
		}
		for(Edge b : B) {
			if(union(b.v1, b.v2)) {
				cnt++;
				blue++;
				if(cnt >= N-1) return blue;
			}
		}
		return blue;
	}
	
	static int getMax(LinkedList<Edge> R, LinkedList<Edge> B) {
		int blue = 0;
		int cnt = 0;
		for(Edge b : B) {
			if(union(b.v1, b.v2)) {
				cnt++;
				blue++;
				if(cnt == N-1) return blue;
			}
		}
		for(Edge r : R) {
			if(union(r.v1, r.v2)) cnt++;
			if(cnt == N-1) return blue;
		}
		return cnt<N-1 ? -1 : blue;
	}
	
	static boolean union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if(p1 == p2) return false;
		parent[p1] = p2;
		return true;
	}
	
	static int find(int v) {
		if(parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}
	
	private static void initialize() {
		parent = new int[N+1];
		for(int i=1; i<=N; i++)
			parent[i] = i;
	}

}

class Edge {
	boolean type;
	int v1, v2;
	
	Edge(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");
		this.type = st.nextToken().charAt(0) == 'R' ? true : false;
		this.v1 = Integer.parseInt(st.nextToken());
		this.v2 = Integer.parseInt(st.nextToken());
	}
}
