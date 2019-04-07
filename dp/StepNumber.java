package dp;
/*
 * [백준] 쉬운 계단 수
 * https://www.acmicpc.net/problem/10844
 */
import java.util.Scanner;



public class StepNumber {
	static final int MOD = 1000000000;
	static int N, Dp[][];
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		Dp = new int[N+1][10];
		
		for(int i=0; i<10; i++)
			Dp[1][i] = 1;
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<10; j++) {
				if (j == 0)
	                Dp[i][0] = Dp[i-1][1] % MOD;
	            else if (j == 9)
	                Dp[i][9] = Dp[i-1][8] % MOD;
	            else
	                Dp[i][j] = (Dp[i-1][j-1] + Dp[i-1][j+1]) % MOD;
			}
		}
		
		int ans = 0;
		for(int i=1; i<10; i++)
			ans = (ans + Dp[N][i]) % MOD;
		System.out.println(ans % MOD);
	}

}
