/*
 * [백준][1976] 여행 가자
 * 17368 KB	128 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static int parent[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				if(st.nextToken().equals("1")) {
					union(i, j);
				}
			}
		}
		System.out.print(ableToGo(br.readLine().trim()) ? "YES" : "NO");
		br.close();
	}
	
	static boolean ableToGo(String route) {
		StringTokenizer st = new StringTokenizer(route, " ");
		int prev = Integer.parseInt(st.nextToken()) - 1;
		while(st.hasMoreTokens()) {
			int cur = Integer.parseInt(st.nextToken()) - 1;
			if(find(prev) != find(cur)) {
				return false;
			}
			prev = cur;
		}
		return true;
	}
	
	static void union(int v1, int v2) {
		parent[find(v1)] = find(v2);
	}
	
	static int find(int v) {
		if(v == parent[v]) return v;
		return parent[v] = find(parent[v]);
	}
}
