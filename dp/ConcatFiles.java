/*
 * [백준] 파일 합치기
 * https://www.acmicpc.net/problem/11066
 */
package dp;
import java.util.Scanner;


public class ConcatFiles {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int K, files[], dp[][];
			K = sc.nextInt();
			files = new int[K+1];
			dp = new int[K+1][K+1];
			for(int i=1; i<=K; i++) {
				files[i] = sc.nextInt();
			}
			// dp[i][j] = i에서부터 j까지 파일을 합칠때 최소 비용
			// 순서: 가장 아래 줄부터, 가장 왼쪽에서부터 계산
			for(int i=K; i>=0; i--) {
				int fileSize = files[i];	// fileSize를 루프 하나당 한 번 더해서 계산
				for(int j=i+1; j<=K; j++) {
					fileSize += files[j];
					int min = Integer.MAX_VALUE;
					for(int k=i; k<j; k++) {
						min = Math.min(min, dp[i][k] + dp[k+1][j] + fileSize);
					}
					dp[i][j] = min;
				}
			}
			System.out.println(dp[1][K]);
		}
		sc.close();
	}
}
