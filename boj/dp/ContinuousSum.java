/*
 * [백준] 연속합
 * https://www.acmicpc.net/problem/1912
 */
import java.util.Scanner;



public class Main {
	static int N, nums[], Dp[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		for(int i=0; i<N; i++)
			nums[i] = sc.nextInt();
		sc.close();
		
		Dp = new int[N];
		Dp[0] = nums[0];
		int max = Dp[0];
		for(int i=1; i<N; i++) {
			Dp[i] = Math.max(Dp[i-1]+nums[i], nums[i]);
			max = Math.max(max, Dp[i]);
		}
		System.out.println(max);
	}
}
