package graph;
/*
 * [백준] 내리막 길
 * https://www.acmicpc.net/problem/1520 
 */
import java.util.Scanner;


public class Downhill {
	static final int[] dx = {1, -1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	static int M, N, map[][], dp[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		map = new int[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		
		dp = new int[M][N];
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				dp[i][j]=-1;
		dp[M-1][N-1] = 1;
		dfs(0, 0);
		
		System.out.println(dp[0][0]);
	}
	
	static int dfs(int cy, int cx) {
		int y, x;
		if(dp[cy][cx]>=0) return dp[cy][cx];
		for(int i=0; i<4; i++) {
			y = cy + dy[i];
			x = cx + dx[i];
			if(y<0 || x<0 || y>=M || x>=N) continue;
			if(map[y][x] < map[cy][cx]) {
				if(dp[cy][cx]==-1) dp[cy][cx]++;
				dp[cy][cx] += dfs(y,x);
			}
		}
		if(dp[cy][cx]<=0) return 0;
		else return dp[cy][cx];
	}

}


