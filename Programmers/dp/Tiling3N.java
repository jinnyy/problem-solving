/*
 * [프로그래머스][level4] 3 x n 타일링
 * https://programmers.co.kr/learn/courses/30/lessons/12902
 */

class Solution {
    static final int MOD = 1000000007;
    
    public int solution(int n) {
        if(n%2 == 1) return 0;
        n /= 2;
        long[] dp = new long[n + 2];
        dp[0] = 1;
        dp[1] = 3;
        for(int i=2; i<=n; i++){
            dp[i] = (3 * dp[i-1]) % MOD;
            dp[i] %= MOD;
            for(int j=0; j<=i-2; j++){
                dp[i] += (2 * dp[j]) % MOD;
                dp[i] %= MOD;
            }
        }
        return (int) dp[n] % MOD;
    }
}
