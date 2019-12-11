/*
 * [백준] 피보나치 함수
 * https://www.acmicpc.net/problem/1003
 */
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int T, N, dp[][];
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		dp = new int[41][2];
    
		dp[0] = new int[] {1, 0};
		dp[1] = new int[] {0, 1};
		for(int i=2; i<=40; i++) {
			for(int j=0; j<2; j++)
				dp[i][j] = dp[i-1][j] + dp[i-2][j];
		}
		
    // print
		for(int testCase=0; testCase<T; testCase++) {
			N = sc.nextInt();
			System.out.println(String.format("%s %s", dp[N][0], dp[N][1]));
		}
		sc.close();	
	}
}
