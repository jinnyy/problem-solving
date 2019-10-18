/*
 * [백준][14502] 연구소
 * 126116 KB	316 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static final int EMPTY = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	static final int GOAL = 3;
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static int N, M, map[][], wCnt, max;
	static LinkedList<int[]> Viruses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		wCnt = 0;
		Viruses = new LinkedList<int[]>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == VIRUS) Viruses.add(new int[] {i, j});
				else if(map[i][j] == WALL) wCnt++;
			}
		}
		br.close();
		
		wCnt += GOAL;
		select(0, 0, -1);
		
		System.out.print(max);
	}
	
	static void select(int depth, int cy, int cx) {
		if(depth == GOAL) {
			int cnt = bfs();
			max = Math.max(max, cnt);
			return;
		}
		// next
		for(int ny=cy; ny<N; ny++) {
			int nx = ny==cy ? cx+1 : 0;
			for(; nx<M; nx++) {
				if(map[ny][nx] != EMPTY) continue;
				map[ny][nx] = WALL;
				select(depth+1, ny, nx);
				map[ny][nx] = EMPTY;
			}
		}
	}

	static int bfs() {
		int cnt = N * M - wCnt;
		boolean[][] visited = new boolean[N][M];
		LinkedList<int[]> Q = new LinkedList<int[]>();
		for(int[] v : Viruses) {
			Q.add(v);
			visited[v[0]][v[1]] = true;
		}
		
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			cnt--;
			for(int i=0; i<4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(ny<0 || nx<0 || ny>=N || nx>=M || map[ny][nx]==WALL || visited[ny][nx]) continue;
				visited[ny][nx] = true;
				Q.add(new int[] {ny, nx});
			}
		}
		return cnt;
	}
}
