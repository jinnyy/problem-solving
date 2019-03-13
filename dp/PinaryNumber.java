package dp;
/*
 * [백준] 이친수
 * https://www.acmicpc.net/problem/2193
 */
import java.util.Scanner;


public class PinaryNumber {
	static int N;
	static long[][] Dp;
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		reader.close();
		Dp = new long[N+1][N+1];
		
		Dp[0][0] = 1;
		Dp[0][1] = 0;
		Dp[1][0] = 0;
		Dp[1][1] = 1;
		
		for(int i=2; i<=N; i++) {
			Dp[i][0] = Dp[i-1][0] + Dp[i-1][1];
			Dp[i][1] = Dp[i-1][0];
		}
		
		System.out.println(Dp[N][0]+Dp[N][1]);
	}
}
