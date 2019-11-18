/*
 * [백준][2589] 보물섬
 * 160280 KB  340 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static boolean visitable[][];
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visitable = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				visitable[i][j] = line.charAt(j)=='L' ? true : false;
			}
		}
		br.close();
		int max = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				max = Math.max(max, bfs(i, j));
			}
		}
		System.out.print(max);
	}
	
	static int bfs(int sy, int sx) {
		if(!visitable[sy][sx]) return -1;
		int max = 1;
		LinkedList<int[]> Q = new LinkedList<int[]>();
		int[][] dist = new int[N][M];
		Q.add(new int[] {sy, sx});
		dist[sy][sx] = 1;
		
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			max = Math.max(max, dist[cur[0]][cur[1]]);
			for(int d=0; d<4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=M || !visitable[ny][nx] || dist[ny][nx]>0) continue;
				dist[ny][nx] = dist[cur[0]][cur[1]] + 1;
				Q.add(new int[] {ny, nx});
			}
		}
		return max - 1;
	}
}
