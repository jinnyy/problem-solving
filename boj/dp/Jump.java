/*
 * [백준] 점프
 * https://www.acmicpc.net/problem/1890
 */
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int N, map[][];
		long dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close();
		dp = new long[N][N];
		dp[0][0]=1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dp[i][j]==0 || (i==N-1 && j==N-1)) continue;
				if(i+map[i][j]<N) dp[i+map[i][j]][j] += dp[i][j];
				if(j+map[i][j]<N) dp[i][j+map[i][j]] += dp[i][j];
			}
		}
		System.out.println(dp[N-1][N-1]);
	}

}
