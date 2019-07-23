/*
 * [프로그래머스][DP] 서울에서 경산까지
 * https://programmers.co.kr/learn/courses/30/lessons/42899
 */
package programmers;


public class SeoulToKyungsan {

	public static int solution(int K, int[][] travel) {
		int N, dp[][];
		N = travel.length;
		dp = new int[N+1][K+1];
		
		for(int i=1; i<=N; i++) {
			for(int k=0; k<=K; k++) {
				int time, money, locMax = -1;
				for(int j=0; j<2; j++) {
					time = travel[i-1][2*j];
					money = travel[i-1][2*j+1];
					if(k>=time && dp[i-1][k-time]!=-1)
						locMax = Math.max(locMax, dp[i-1][k-time] + money);
					else
						locMax = Math.max(locMax, -1);
				}
				dp[i][k] = locMax;
			}
		}
		return dp[N][K];
	}
}

