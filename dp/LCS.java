package dp;
/*
 * [백준] LCS
 * https://www.acmicpc.net/problem/9251
 */
import java.util.Scanner;


public class LCS {
	public static void main(String[] args) {
		int dp[][], N, M;
		String str[];
		str = new String[2];
		Scanner sc = new Scanner(System.in);
		str[0] = sc.nextLine().trim();
		str[1] = sc.nextLine().trim();
		sc.close();
		
		N = str[0].length();
		M = str[1].length();
		dp = new int[N+1][M+1];
    		// 계산 시작
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
        			// 현재 위치의 문자가 서로 같다면
				if (str[0].charAt(i-1)==str[1].charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
        			// 서로 다르다면
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		System.out.println(dp[N][M]);
	}

}
