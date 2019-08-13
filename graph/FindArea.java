/*
 * [백준][2583번] 영역 구하기
 * 13556KB 88ms
 */
package graph;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;


public class FindArea {
	static final int[] dy = {0, 0, 1, -1};
	static final int[] dx = {1, -1, 0, 0};
	static int M, N, K, area;
	static boolean map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[M][N];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y1, y2, x1, x2;
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			for(int y=y1; y<y2; y++) {
				for(int x=x1; x<x2; x++) {
					map[y][x] = true;
				}
			}
		}
		
		// 연산
		int cnt = 0;
		PriorityQueue<Integer> areas = new PriorityQueue<Integer>();
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(!map[i][j]) {
					area=0;
					dfs(i, j);
					areas.add(area);
					cnt++;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(cnt);
		sb.append("\n");
		while(!areas.isEmpty()) {
			sb.append(areas.poll());
			sb.append(" ");
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(sb.toString());
		bw.newLine();
		bw.flush();	
	}
	
	static void dfs(int cy, int cx) {
		map[cy][cx] = true;
		area++;
		for(int i=0; i<4; i++) {
			int y = cy + dy[i];
			int x = cx + dx[i];
			if(y<0 || x<0 || y>=M || x>=N) continue;
			if(!map[y][x]) dfs(y, x);
		}
	}
}
