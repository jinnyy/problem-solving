/*
 * 알약
 * https://www.acmicpc.net/problem/4811
 */
package dp;

import java.util.Scanner;


public class Pill {
	public static long[][] dp;
	public static long[] ans;
	
	public static void main(String[] args) {
		dp = new long[61][61];
		dp[1][1] = 1;
		ans = new long[61];
		ans[1] = 0;
		
		for(int i=2;i<=60;++i){
	        for(int j=(i-1)/2+1;j<=i;++j){
	            dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
	            if(j<=i/2)ans[i]+=dp[i][j];
	        }
	    }
		Scanner keyboard = new Scanner(System.in);
		int N = keyboard.nextInt();
		while(N!=0) {
			System.out.println(ans[2*N]);
			N = keyboard.nextInt();
		}
		keyboard.close();
	}
}
