/*
 * [백준] 암호 코드
 * https://www.acmicpc.net/problem/2011
 */
import java.util.Scanner;


public class Main {
	static final int MOD = 1000000;

	public static void main(String[] args) {
		String code;
		int N, dp[];
		Scanner sc = new Scanner(System.in);
		code = sc.next();
		sc.close();
		
		// 예외처리
		if(code.charAt(0)=='0') {
			System.out.println(0);
			return;
		}
		
		// 계산
		N = code.length();
		dp = new int[N];
		dp[0] = 1;
		for(int i=1; i<N; i++) {
			char digit = code.charAt(i);
			if(digit-'0'!=0) {
				dp[i] = dp[i-1];
			}
			// (20이상 26 이하) OR (10이상 19이하)
			if((digit-'0'<=6 && code.charAt(i-1)=='2') || code.charAt(i-1)=='1') {
				if(i>1) dp[i] += dp[i-2];
				else dp[i] += 1;
				dp[i] %= MOD;
			}
		}
		System.out.println(dp[N-1]);
	}
}
