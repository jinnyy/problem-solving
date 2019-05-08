package dp;
/*
 * [백준] 가장 긴 감소하는 부분 수열
 * 
 */
import java.util.Scanner;


public class LongestDecreasing {

	public static void main(String[] args) {
		int N, A[], dp[], max;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N];
		for(int i=0; i<N; i++)
			A[i] = sc.nextInt();
		sc.close();
		
		dp = new int[N+1];
		
		max = 0;
		for(int i=0; i<N; i++) {
			int min = 0;
			for(int j=0; j<i; j++) {
				if(A[i] < A[j])
					min = Math.max(min, dp[j]);
			}
			dp[i] = min + 1;
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
