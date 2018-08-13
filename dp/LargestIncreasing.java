package dp;
/*
 * 가장 큰 증가부분수열
 * https://www.acmicpc.net/problem/11055
 */
import java.util.Scanner;


public class LargestIncreasing {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		for(int i=0; i<N; i++)
			A[i] = sc.nextInt();
		int[] dp = new int[N];
		int totMax = 0;
		
		// calculation
		for(int i=0; i<N; i++) {
			int loopMax = 0;
			for (int j = 0; j < i; j++){
	            if (A[i]>A[j]){
	                if (loopMax < dp[j])
	                	loopMax = dp[j];
	            }
	        }
	        dp[i] = loopMax + A[i];
	        if (totMax < dp[i])
	            totMax = dp[i];
		}
		System.out.println(totMax);
	}

}
