/*
 * [백준] 가장 큰 정사각형
 * https://www.acmicpc.net/problem/1915
 */
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int n, m, dp[][], max=0;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		dp = new int[n][m];
		for(int i=0; i<n; i++) {
			String temp = sc.next();
			for(int j=0; j<m; j++) {
				dp[i][j] = temp.charAt(j) - '0';
				if(dp[i][j]==1) max=1;
			}
		}
		sc.close();
		
		// calculation	
		for(int i=1; i<n; i++) {
			for(int j=1; j<m; j++){
				if(dp[i][j]>0) {
					int localMin = Math.min(dp[i-1][j-1], dp[i-1][j]);
					dp[i][j] = Math.min(localMin, dp[i][j-1]) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		
		System.out.println(max*max);
	}

}
