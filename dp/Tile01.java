package dp;
/*
 * [백준] 01타일
 * https://www.acmicpc.net/problem/1904
 */
import java.util.Scanner;


public class Tile01 {
	static final int MOD = 15746;

	public static void main(String[] args) {
		int N, dp[];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		dp = new int[N+1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2; i<=N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
			dp[i] %= MOD;
		}
		
		System.out.println(dp[N]);
	}

}
