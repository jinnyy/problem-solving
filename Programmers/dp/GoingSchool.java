/*
 * [프로그래머스] 등굣길
 * https://programmers.co.kr/learn/courses/30/lessons/42898
 */
class Solution {
    static final int MOD = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
		boolean[][] map = new boolean[n+1][m+1];
		int[][] dp = new int[n+1][m+1];
		for(int[] puddle: puddles) {
			map[puddle[1]][puddle[0]] = true;
		}
		dp[1][0] = 1;
		
		// calculate
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(map[i][j]) continue;
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
			}
		}
		return dp[n][m];
	}
}
