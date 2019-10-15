/*
 * [SWEA][5521] 상원이의 생일파티
 * 36,000 kb  193 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			// read
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Friend[] friends = new Friend[N+1];
			for(int i=1; i<=N; i++) {
				friends[i] = new Friend();
			}
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				friends[a].add(b);
				friends[b].add(a);
			}
			
			// bfs
			LinkedList<Integer> Q = new LinkedList<Integer>();
			boolean[] visited = new boolean[N+1];
			Q.add(1);
			visited[1] = true;
			friends[1].distance = 0;
			int cnt = 0;
			while(!Q.isEmpty()) {
				int cur = Q.poll();
				if(friends[cur].distance <= 2) {
					if(cur>1) cnt++;
				} else continue;
				
				for(int next : friends[cur].adj) {
					if(visited[next]) continue;
					visited[next] = true;
					friends[next].distance = friends[cur].distance + 1;
					Q.add(next);
				}
			}
			
			// write
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(cnt);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
}

class Friend {
	LinkedList<Integer> adj;
	int distance;
	
	Friend() {
		this.adj = new LinkedList<Integer>();
		this.distance = Integer.MAX_VALUE;
	}
	
	void add(int f) {
		adj.add(f);
	}
}
