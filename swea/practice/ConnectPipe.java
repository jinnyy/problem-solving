/*
 * [SWEA][4340] 파이프 연결
 * 123,560 kb 2,852 ms
 */
package practice;
import java.util.Scanner;


public class ConnectPipe {
	static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	static final int[] dy = {-1, 1, 0, 0};	// 상 하 좌 우
	static final int[] dx = {0, 0, -1, 1};
	static int N, map[][], Min;
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			// read
			N = sc.nextInt();
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j]==0) continue;
					if(map[i][j]<3) map[i][j] = 1;
					else map[i][j] = 2;
				}
			}
			
			// calculate
			Min = 2501;
			visited = new boolean[N][N];
			dfs(N-1, N-1, 1, RIGHT);
			
			
			// write
			System.out.println(String.format("#%d %d", test_case, Min));
		}
		sc.close();
	}
	
	
	private static void dfs(int cy, int cx, int depth, int from) {
		if(cy==0 && cx==0 && ((map[cy][cx]==1 && from==RIGHT) ||  (map[cy][cx]==2 && from==DOWN))) {
			if(depth < Min) Min = depth;
			return;
		}
		visited[cy][cx] = true;
		
		int dirs[];
		if(map[cy][cx]==1) {
			if(from==UP) dirs = new int[] {DOWN};
			else if(from==DOWN) dirs = new int[] {UP};
			else if(from==LEFT) dirs = new int[] {RIGHT};
			else dirs = new int[] {LEFT};
		} else {
			if(from==UP || from==DOWN) dirs = new int[] {LEFT, RIGHT};
			else dirs = new int[] {UP, DOWN};
		}
		// visit
		for(int to : dirs) {
			int y = cy + dy[to];
			int x = cx + dx[to];
			// 최단거리로 마쳐도 결과 depth값이 현재 min값보다 크다면 건너뛴다.
			if(y<0 || x<0 || y>=N || x>=N || map[y][x]==0 || visited[y][x] || y+x+depth+1 >= Min) continue;
			dfs(y, x, depth+1, convertDir(to));
		}
		
		visited[cy][cx] = false;
	}
	
	private static int convertDir(int dir) {
		if(dir==UP) return DOWN;
		if(dir==DOWN) return UP;
		if(dir==LEFT) return RIGHT;
		return LEFT;
	}
}

