package dp;
/*
 * [백준] 제곱수의 합
 * https://www.acmicpc.net/problem/1699
 */
import java.util.Scanner;


public class SumOfSquareNumber {

	public static void main(String[] args) {
		int N, dp[];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		dp = new int[N+1];
		for (int i=1; i<=N; i++) {
	        for (int j=1; j*j<=i; j++) {
	            if (dp[i] > dp[i-j*j]+1 || dp[i]==0)
	                dp[i] = dp[i-j*j]+1;
	        }
	    }
		System.out.println(dp[N]);
	}
}
