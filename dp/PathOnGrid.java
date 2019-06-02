package dp;
/*
 * [백준] 격자상의 경로
 * https://www.acmicpc.net/problem/10164
 */
import java.util.Scanner;



public class PathOnGrid {

	public static void main(String[] args) {
		int N, M, K, dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt() - 1;
		sc.close();
		int n = K / M + 1;
		int m = K % M + 1;
		
		
		dp = new int[N+1][M+1];
		dp[0][1] = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if (((n<=i || j<=m) && (i<=n || m<=j)))
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		System.out.println(dp[N][M]);
	}

}
