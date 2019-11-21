/*
 * [백준] 오르막 수
 * https://www.acmicpc.net/problem/11057
 */
import java.util.Scanner;


public class Main {
	static final int MOD = 10007;

	public static void main(String[] args) {
		int N, dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		dp = new int[1001][10];
    // dp[i][j]: i자리수 숫자에서 j로 끝나는 경우의 수
		for(int i=0; i<10; i++)
			dp[1][i] = 1;
		for(int i=1; i<N; i++) {
			for(int j=0; j<10; j++) {
				for(int k=j; k<10; k++) {
					dp[i+1][k] = (dp[i+1][k] + dp[i][j]) % MOD;
				}
			}
		}
		
		int sum = 0;
		for(int i=0; i<10; i++) {
      sum += dp[N][i];
      sum %= MOD;
		}
		System.out.println(sum%MOD);
	}

}
