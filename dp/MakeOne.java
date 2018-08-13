package dp;
/*
 * 1로 만들기
 * https://www.acmicpc.net/problem/1463
 */
import java.util.Scanner;

public class MakeOne {
	public static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		dp = new int[N+1];
		for(int i=0; i<N+1; i++)
			dp[i] = Integer.MAX_VALUE;
		
		System.out.println(makeOne(N));
	}
	
	public static int makeOne(int n) {
		if(n==1) return 0;
		if(dp[n]!=Integer.MAX_VALUE) return dp[n];
		if(n%3==0) dp[n] = Math.min( dp[n], makeOne((int)n/3)+1 );
		if(n%2==0) dp[n] = Math.min( dp[n], makeOne((int)n/2)+1 );
		dp[n] = Math.min(dp[n], makeOne(n-1)+1);
		return dp[n];
	}
}
