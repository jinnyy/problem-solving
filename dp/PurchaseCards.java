package dp;
/*
 * [백준] 카드 구매하기
 * https://www.acmicpc.net/problem/11052
 */
import java.util.Scanner;


public class PurchaseCards {
	static int N, prices[], Dp[];
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		prices = new int[N+1];
		for(int i=1; i<=N; i++)
			prices[i] = sc.nextInt();
		sc.close();
		
		Dp = new int[N+1];
		Dp[0] = 0;
		for(int i=1; i<=N; i++) {
			int max = prices[i];
			for(int j=1; j<=i; j++) {
				max = Math.max(max, Dp[i-j] + prices[j]);
			}
			Dp[i] = max;
		}
		System.out.println(Dp[N]);
	}

}
