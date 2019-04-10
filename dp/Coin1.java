package dp;
/*
 * [백준] 동전 1
 * https://www.acmicpc.net/problem/2293
 */
import java.util.Scanner;


public class Coin1 {

	public static void main(String[] args) {
		int n, k, coins[], Dp[];
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		coins = new int[n+1];
		for(int i=0; i<n; i++)
			coins[i] = sc.nextInt();
		sc.close();
		
		// calculate
		Dp = new int[k+1];
		Dp[0] = 1;
		// i번째 동전까지 사용
		for (int i=0; i<n; i++) {
			// j라는 숫자를 만듦
		    for (int j=1; j<=k; j++) {
		        if (j >= coins[i]) {
		            Dp[j] += Dp[j-coins[i]];
		        }
		    }
		}
		System.out.println(Dp[k]);
	}
}
