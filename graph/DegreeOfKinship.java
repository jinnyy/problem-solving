/*
 * [백준][2644] 촌수계산
 * 13168KB 72ms
 */
package graph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class DegreeOfKinship {

	public static void main(String[] args) throws Exception {
		int N, from, goal, M, map[];
		boolean adj[][];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		from = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		adj = new boolean[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x][y] = true;
			adj[y][x] = true;
		}
		br.close();
		
		// 계산
		map = new int[N+1];
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.add(from);
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			if(cur==goal) break;
			for(int to=1; to<=N; to++) {
				if(adj[cur][to] && map[to]==0) {
					Q.add(to);
					map[to] = map[cur]+1;
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		sb.append(map[goal]==0? -1: map[goal]);
		bw.append(sb.toString());
		bw.close();
	}

}
