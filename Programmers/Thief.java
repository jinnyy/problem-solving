/*
 * [프로그래머스][DP] 도둑질
 * https://programmers.co.kr/learn/courses/30/lessons/42897
 */
package programmers;


public class Thief {

	public static int solution(int[] money) {
		int N = money.length;
		int[] dp = new int[N];
		int[] dp2 = new int[N];
		boolean[] hasFirst = new boolean[N];
		
		
		dp[0] = money[0];
		hasFirst[0] = true;
		if(dp[0] > money[1]) {
			dp[1] = dp[0];
			hasFirst[1] = true;
		}
		else
			dp[1] = money[1];
		dp2[1] = money[1];
		
		for(int i=2; i<N-1; i++) {
			dp2[i] = Math.max(dp2[i-1], dp2[i-2]+money[i]);
			if (dp[i-1] > dp[i-2]+money[i]) {
				dp[i] = dp[i-1];
				hasFirst[i] = hasFirst[i-1];
			}
			else if(dp[i-1] < dp[i-2]+money[i]) {
				dp[i] = dp[i-2] + money[i];
				hasFirst[i] = hasFirst[i-2];
			}
			else {
				dp[i] = dp[i-1];
				hasFirst[i] = hasFirst[i-2] && hasFirst[i-1];
			}
			
		}
		if(hasFirst[N-3]) dp[N-1] = Math.max(dp[N-2], dp[N-3] - dp[0] + money[N-1]);
		else dp[N-1] = Math.max(dp[N-2], dp[N-3] + money[N-1]);
		if(N%2==0) dp[N-1] = Math.max(dp[N-1], dp2[N-3]+money[N-1]);
		return dp[N-1];
	}
}
