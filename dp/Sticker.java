package dp;
/*
 * [백준] 스티커
 * https://www.acmicpc.net/problem/9465
 */
import java.util.Scanner;


public class Sticker {
	
	public static void main(String[] args) {
		int T, n, stickers[][], Dp[][];
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int testCase=1; testCase<=T; testCase++) {
			n = sc.nextInt();
			stickers = new int[2][n];
			Dp = new int[2][n];
			for(int i=0; i<2; i++) {
				for(int j=0; j<n; j++)
					stickers[i][j] = sc.nextInt();
			}
			
			// calculate
			for(int j=0; j<n; j++) {
				int max = 0;
				for(int i=0; i<2; i++) {
					if(j==0) { Dp[i][j] = stickers[i][0]; continue; }
					if(j==1) { Dp[i][j] = Dp[(i+1)%2][j-1]+stickers[i][j]; continue; }
					max = Math.max(Dp[(i+1)%2][j-2], Dp[i][j-2]);
					max = Math.max(max, Dp[(i+1)%2][j-1]);
					Dp[i][j] = max + stickers[i][j];
				}
			}
			System.out.println(Math.max(Dp[0][n-1], Dp[1][n-1]));
		}
		sc.close();
	}
}
