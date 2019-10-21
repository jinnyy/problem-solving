/*
 * [SWEA][5685][Professional] 초등학생
 * 24,760 kb  158 ms
 */
 
// 참고: 해당 문제에서 Scanner가 아닌 다른 IO 사용시 런타임 에러 (메모리) 발생
import java.util.Scanner;


public class Solution {
	static final int MOD = 1234567891;
	static int seq[];
    static long dp[][];
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int N = sc.nextInt();
			seq = new int[N];
			for(int i=0; i<N-1; i++) {
				seq[i] = sc.nextInt();
			}
			int goal = sc.nextInt();
			dp = new long[2][21];
			count(N);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(dp[(N-2)%2][goal]);
			System.out.println(sb.toString());
		}
		sc.close();
	}
	
	
	static void count(int N) {
		dp[0][seq[0]] = 1;
		for(int idx=1; idx<N-1; idx++) {
			int d = idx % 2;
			int pd = (idx+1) % 2;
			int cur = seq[idx];
			for(int result=0; result<=20; result++) {
				if(dp[pd][result] != 0) {
					if(result - cur >= 0) {
						long loc = dp[d][result - cur] + dp[pd][result];
						dp[d][result - cur] = (loc % MOD);
					}
					if(result + cur <= 20) {
						long loc = dp[d][result + cur] + dp[pd][result];
						dp[d][result + cur] = (loc % MOD);
					}
				}
			}
			dp[pd] = new long[21];
		}
	}
}
