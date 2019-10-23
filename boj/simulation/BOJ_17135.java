/*
 * [백준][17135] 캐슬 디펜스
 * 40624 KB	188 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static final int[] dy = {0, -1, 0};
	static final int[] dx = {-1, 0, 1};
	static int N, M, D, max, MAX;
	static boolean mapOrig[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		mapOrig = new boolean[N+1][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				if(st.nextToken().equals("1")) {
					mapOrig[i][j] = true;
					MAX++;
				}
			}
		}
		br.close();
		max = 0;
		
		select(0, -1, new int[3]);
		System.out.print(max);
	}
	
	static void select(int depth, int cur, int[] loc) {
		if(depth == 3) {
			int[][] archers = new int[3][2];
			boolean[][] map = new boolean[N+1][M];
			for(int i=0; i<=N; i++) {
				map[i] = mapOrig[i].clone();
			}
			for(int i=0; i<3; i++) {
				archers[i][0] = N;
				archers[i][1] = loc[i];
			}
			int sum = 0;
			while(archers[0][0] > 0 && sum<MAX) {
				int[][] enemies = new int[3][2];
				for(int i=0; i<3; i++) {
					enemies[i] = findEnemy(archers[i], map);
				}
				sum += attack(enemies, map);
				move(archers);
			}
			max = Math.max(max, sum);
			return;
		}
		
		for(int next=cur+1; next<M; next++) {
			loc[depth] = next;
			select(depth+1, next, loc);
			loc[depth] = 0;
		}
	}
	
	// 궁수를 올림
	static void move(int[][] locs) {
		for(int[] loc : locs) {
			loc[0]--;
		}
	}
	
	static int attack(int[][] enemies, boolean[][] map) {
		int cnt = 0;
		for(int[] E : enemies) {
			if(E == null) continue;
			if(map[E[0]][E[1]]) {
				cnt++;
				map[E[0]][E[1]] = false;
			}
		}
		return cnt;
	}
	
	static int[] findEnemy(int[] archer, boolean[][] map) {
		boolean[][] visited = new boolean[N+1][M];
		LinkedList<int[]> Q = new LinkedList<int[]>();
		Q.add(archer);
		int sy = archer[0];
		int sx = archer[1];
		visited[sy][sx] = true;
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			int cy = cur[0];
			int cx = cur[1];
			if(cy!=sy && map[cy][cx]) return new int[] {cy, cx};
			for(int i=0; i<3; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if(ny<0 || nx<0 || nx>=M || visited[ny][nx] || distance(sy, sx, ny, nx) > D) continue;
				visited[ny][nx] = true;
				Q.add(new int[] {ny, nx});
			}
		}
		return null;
	}
	
	static int distance(int cy, int cx, int ny, int nx) {
		return Math.abs(cy - ny) + Math.abs(cx - nx);
	}
}
