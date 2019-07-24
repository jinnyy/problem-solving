/*
 * [SWEA] 등산로 조성
 * (1949번) https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq
 */
package swea;
import java.util.Scanner;


public class Trail {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int N, K, map[][], trail[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			N = sc.nextInt();
			K = sc.nextInt();
			map = new int[N][N];
			trail = new int[N][N];
			int max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					max = Math.max(max, map[i][j]);
				}
			}
			int answer = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==max) {
						// dfs
						boolean[][] visited = new boolean[N][N];
						answer = Math.max(answer, dfs(i, j, visited, false));
					}
				}
			}
			System.out.println(String.format("#%d %d", test_case, answer));
		}
		sc.close();
	}
	
	static int dfs(int cy, int cx, boolean[][] visited, boolean lowered) {
		visited[cy][cx] = true;
		int ret = 0;
		for(int i=0; i<4; i++) {
			int y = cy + dy[i];
			int x = cx + dx[i];
			if(y<0 || y>=N || x<0 || x>=N || visited[y][x]) continue;
			
			// 더 크거나 같은 경우
			if (map[cy][cx] <= map[y][x]) {
				if (!lowered) {
					for(int depth=1; depth<=K; depth++) {
						if(map[y][x] - depth < map[cy][cx]) {
							map[y][x] -= depth;
							ret = Math.max(ret, dfs(y, x, visited, true));
							map[y][x] += depth;
						}
					}
				}
			}
			// (새로운 높이가) 작은 경우
			else {
				ret = Math.max(ret, dfs(y, x, visited, lowered));
			}
		}
		visited[cy][cx] = false;
		return ret + 1;
	}
}
