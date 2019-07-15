/*
 * [프로그래머스][DP] 정수 삼각형
 * https://programmers.co.kr/learn/courses/30/lessons/43105
 */
package programmers;


public class IntTriangle {
    public static int solution(int[][] triangle) {
        // 가장 아래 줄부터 차례로 계산한다.
        // dp[i%2][j] = 아래에서부터 i번째 줄 j번째 칸까지 계산했을 때 최댓값 
        int N, dp[][];
        N = triangle.length;
        dp = new int[2][N];
        dp[0][0] = triangle[0][0];
        // 가장 아래 줄의 dp 값은 triangle의 값과 같다.
        for(int j=0; j<N; j++)
        	dp[(N-1)%2][j] = triangle[N-1][j];
        // 나머지 줄 계산
        for(int i=N-2; i>=0; i--) {
        	for(int j=i; j>=0; j--) {
        		dp[i%2][j] = Math.max(dp[(i+1)%2][j+1], dp[(i+1)%2][j]) + triangle[i][j];
        	}
        }
        
        return dp[0][0];
    }
}
