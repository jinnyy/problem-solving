/*
 * [백준] 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 */
package dp;
import java.util.Scanner;


public class Backpack {

	public static void main(String[] args) {
		int N, K, stuffs[][], dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		stuffs = new int[N][2];
		
		for(int i=0; i<N; i++) {
			stuffs[i][0] = sc.nextInt();
			stuffs[i][1] = sc.nextInt();
		}
		sc.close();
		
		// dp[i][j] = i번째 물건까지 선택하고, j만큼의 무게만큼 담았을 때의 최대 가치
		dp = new int[N+1][K+1];
		for(int i=0; i<N; i++) {
			for(int j=0; j<=K; j++) {
				dp[i+1][j] = dp[i][j];				
				if(j >= stuffs[i][0])
					dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j-stuffs[i][0]] + stuffs[i][1]);
			}
		}
		System.out.println(dp[N][K]);
	}

}
