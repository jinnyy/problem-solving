/*
 * [백준][12100] 2048 (Easy)
 * 21080 KB	236 ms
 */
package boj_samsungswtest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Easy2048 {
	static final int[] dy = {1, -1, 0, 0};
	static final int[] dx = {0, 0, 1, -1};
	static int N, map[][], max, MAX;

	public static void main(String[] args) throws Exception {
		// 1. read
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		br.close();
		MAX = max << N;

		// 2. calculate
		dfs(0);
		
		// 3. write
		System.out.print(max);
	}
	
	static void dfs(int depth) {
		if(depth>=5) {
			return;
		}
		// 1) copy (caching)
		int[][] map_cache = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map_cache[i][j] = map[i][j];
			}
		}
			
		// 2) try 4 dirs
		for(int dir=0; dir<4; dir++) {
			push(dir, map);
			move(dir, map);
			push(dir, map);
			dfs(depth+1);
			if(max >= MAX) return;
			// reset
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = map_cache[i][j];
				}
			}
		}
		
	}
	
	static void move(int dir, int[][] map) {
		int y=0, x=0;
		if(dir==1) y = N-1;
		else if(dir==3) x = N-1;
		
		int ly, lx;
		for(int i=0; i<N; i++) {
			ly = y;
			lx = x;
			for(int j=0; j<N; j++) {
				if(!onEdge(ly+dy[dir], lx+dx[dir]) && map[ly][lx] == map[ly+dy[dir]][lx+dx[dir]]) {
					map[ly][lx] *= 2;
					map[ly+dy[dir]][lx+dx[dir]] = 0;
				}
				ly += dy[dir];
				lx += dx[dir];
				if(onEdge(ly, lx)) break;
			}
			if(dir<2) x++;
			else y++;
			if(onEdge(y, x)) break;
		}
	}
	
	static void push(int dir, int[][] map) {
		int cnt = 0;
		int y=0, x=0;
		if(dir==1) y = N-1;
		else if(dir==3) x = N-1;
		int ly, lx;
		for(int i=0; i<N; i++) {
			cnt = 0;
			ly = y;
			lx = x;
			for(int j=0; j<N; j++) {
				max = Math.max(max, map[ly][lx]);
				if(map[ly][lx]==0) 
					cnt++;
				else if(cnt>0) {
					map[ly - cnt * dy[dir]][lx - cnt * dx[dir]] = map[ly][lx];
					map[ly][lx] = 0;
				}
				ly += dy[dir];
				lx += dx[dir];
				if(onEdge(ly, lx)) break;
			}
			if(dir<2) x++;
			else y++;
			if(onEdge(y, x)) break;
		}
	}
	
	static boolean onEdge(int y, int x) {
		return y<0 || x<0 || y>=N || x>=N;
	}
}
