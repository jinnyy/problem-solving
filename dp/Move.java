package dp;
/*
 * [백준] 이동하기
 * https://www.acmicpc.net/problem/11048
 */
import java.util.Scanner;


public class Move {
	static final int[] dx = {-1, 0, -1};
	static final int[] dy = {0, -1, -1};

	public static void main(String[] args) {
		int N, M, rooms[][], dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		rooms = new int[N+1][M+1];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				rooms[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		// calculate
		dp = new int[N][M];
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				int max = 0;
				for(int i=0; i<3; i++) {
					int curY = y + dy[i];
					int curX = x + dx[i];
					if(curY>=0 && curX>=0 && curY<N && curX<M) {
						max = Math.max(max, dp[curY][curX]);
					}
				}
				dp[y][x] = max + rooms[y][x];
			}
		}
		System.out.println(dp[N-1][M-1]);
	}
}
