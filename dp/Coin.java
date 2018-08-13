package dp;
/*
 * µ¿Àü
 * https://www.acmicpc.net/problem/9084
 */

import java.util.Scanner;
import java.util.Arrays;


public class Coin {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int i, j;
		int T = Integer.parseInt(keyboard.nextLine());
		
		while(T-- > 0) {
			int N, M;
			int[] coin;
			
			N = Integer.parseInt(keyboard.nextLine());
			coin = new int[N+1];
			String[] str = keyboard.nextLine().split(" ");
			for(i=0; i<N; i++)
				coin[i] = Integer.parseInt(str[i]);
			M = Integer.parseInt(keyboard.nextLine());
			
			int[] dp = new int[M+1];
			Arrays.fill(dp, 0);
			
			// calcalate
			dp[0] = 1;
			for(i=0; i<N; i++) {
				for(j=coin[i]; j<=M; j++)
					dp[j] += dp[j - coin[i]];
			}
			System.out.println(dp[M]);
		}
		keyboard.close();
	}
}
