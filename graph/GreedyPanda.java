package graph;
/*
 * [백준] 욕심쟁이 판다
 * - dfs, dynamic programming
 */
import java.util.Scanner;


public class GreedyPanda {
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static int N, trees[][], dp[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		trees = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				trees[i][j] = sc.nextInt();
		}
		sc.close();
		
		// calculation
		int K = 0; // 판다가 살 수 있는 최대 일수
		dp = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// [i][j]에서 시작하는 최장 길이 구하기
				K = Math.max(K, dfs(i, j));
			}
		}
		System.out.println(K);
	}
	
	static int dfs(int cy, int cx) {
		if(dp[cy][cx]!=0)
			return dp[cy][cx];
		dp[cy][cx] = 1;
		
		int y, x;
		for(int i=0; i<4; i++) {
			y = cy + dy[i];
			x = cx + dx[i];
			if(y<0 || x<0 || y>=N || x>=N)
				continue;
			if(trees[cy][cx]<trees[y][x]) {
				dp[cy][cx] = Math.max(dp[cy][cx], dfs(y, x)+1);
			}
		}
		return dp[cy][cx];
	}

}
