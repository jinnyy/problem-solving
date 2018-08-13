package dp;
/*
 * 포도주 시식
 * https://www.acmicpc.net/problem/2156
 */
import java.util.Scanner;


public class Wine {
	public static int[] dp;
	public static int[] wine;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[N+1];
		wine = new int[N+1];
		for(int i=1; i<=N; i++) {
			dp[i] = -1;
			wine[i] = sc.nextInt();
		}
		sc.close();
		
		for (int i=1; i<3 && i<=N; i++){
	        if (i == 1)
	            dp[i] = wine[i];
	        else
	            dp[i] = wine[i] + wine[i - 1];
	    }
	 
	    for (int i=3; i <= N; i++){
	        int result = 0;
	        result = Math.max(wine[i] + dp[i - 2], dp[i - 1]);
	        result = Math.max(result, wine[i] + wine[i - 1] + dp[i - 3]);
	        dp[i] = result;
	    }	
		
		System.out.println(dp[N]);
	}
}