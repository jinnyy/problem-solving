/*
 * [백준][14500] 테트로미노
 * 32956 KB	528 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Tetronomio {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	
	static final int GOAL = 4;
	static int N, M, map[][], max;

	public static void main(String[] args) throws Exception {
		// read
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		// calculate
		max = 0;
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				go(i, j, 1, 0, visited, -1, true);
			}
		}
		
		// write
		System.out.print(max);
	}
	
	static void go(int cy, int cx, int depth, int score, boolean[][] visited, int dir, boolean sameDir) {
		score += map[cy][cx];
		if(depth == GOAL-1 && sameDir) {
			int local = 0;
			// 세로
			if(dir<=1) {
				int d = dir==0 ? 1 : -1;
				if(cy+d>=0 && cy+d<N) {
					if(cx-1 >= 0) local = Math.max(local, map[cy + d][cx-1]);
					if(cx+1 < M) local = Math.max(local, map[cy + d][cx+1]);
				}
			}
			// 가로
			else {
				int d = dir==2 ? 1 : -1;
				if(cx+d>=0 && cx+d<M) {
					if(cy-1 >= 0) local = Math.max(local, map[cy-1][cx + d]);
					if(cy+1 < N) local = Math.max(local, map[cy+1][cx + d]);
				}
			}
			if(local>0 && max < score + local)
				max = score + local;
		}
		if(depth == GOAL) {
			if(max < score) max = score;
			return;
		}
    
		visited[cy][cx] = true;
		for(int i=0; i<4; i++) {
			boolean sameDirNext = i==dir || dir<0 ? true : false;
			int y = cy + dy[i];
			int x = cx + dx[i];
			if(y<0 || x<0 || y>=N || x>=M || visited[y][x]) continue;
			go(y, x, depth+1, score, visited, i, sameDirNext);
		}
		visited[cy][cx] = false;
	}
}
