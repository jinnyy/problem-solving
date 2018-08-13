package dp;
/*
 * 2XN 타일링 2
 * https://www.acmicpc.net/problem/11727
 */
import java.util.Scanner;


public class Tiling2 {
	public static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		dp = new int[N+2];
		dp[0] = dp[1] = 1;
		dp[2] = 3;
		
		System.out.println(fill(N));
	}
	
	public static int fill(int n) {
		if(dp[n]!=0) return dp[n];
		dp[n] = ((fill(n-2)*2)%10007) + (fill(n-1)%10007);
		return dp[n];
	}
}
