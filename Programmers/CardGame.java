package programmers;


public class CardGame {
	public static int solution(int[] left, int[] right) {
		int N, dp[][];
		N = left.length;
		dp = new int[N+1][N+1];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dp[i+1][j+1] = Math.max(dp[i][j], dp[i][j+1]);
				if(i<N-1 && left[i+1] > right[j]) {
					dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i+1][j] + right[j]);
				}
			}
		}
		return dp[N][N];
	}
}
