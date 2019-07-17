/*
 * [프로그래머스][DP] 타일 장식
 * https://programmers.co.kr/learn/courses/30/lessons/43104
 */
package programmers;


public class TileDecoration {
	public static void main(String[] args) {
		System.out.println(solution(5));
	}

	public static long solution(int N) {
		// dp[i] : i번째 사각형의 변의 길이
		long[] dp = new long[81];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		for(int i=3; i<=N; i++) {
			  dp[i] = dp[i-1] + dp[i-2];
		}
		// 사각형의 둘레의 길이를 리턴
		return (dp[N-1] + 2 * dp[N]) * 2;
    }
}
