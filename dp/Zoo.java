package dp;
/*
 * [백준] 동물원
 * 
 */
import java.util.Scanner;


public class Zoo {
	static final int MOD = 9901;

	public static void main(String[] args) {
		int N, dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		dp = new int[N+1][3];
		for(int i=0; i<3; i++)
			dp[1][i] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
			dp[i][1] = dp[i - 1][0] + dp[i - 1][2] % MOD;
			dp[i][2] = dp[i - 1][0] + dp[i - 1][1] % MOD;
		}
		System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % MOD);
	}

}
