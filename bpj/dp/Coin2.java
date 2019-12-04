/*
 * [백준] 동전 2
 * https://www.acmicpc.net/problem/2294
 */
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		int n, k, coins[], dp[];
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		coins = new int[n];
		dp = new int[k+1];
		for(int i=0; i<n; i++) {
			coins[i] = sc.nextInt();
		}
		sc.close();
		
		for(int coin: coins) {
			if(coin>k) continue;
			dp[coin] = 1;
		}
		
		for(int i=1; i<=k; i++) {
			for(int coin: coins) {
				if(i-coin<0 || dp[i-coin]==0) continue;
				if(dp[i]==0)
					dp[i] = dp[i-coin] + 1;
				else
					dp[i] = Math.min(dp[i], dp[i-coin]+1);
			}
		}
		System.out.println(dp[k]==0? -1: dp[k]);
	}

}
