/*
 * [백준] 가장 큰 증가 부분 수열
 * https://www.acmicpc.net/problem/11055
 */
import java.util.Scanner;


public class Main {
	static int N, Arr[], Dp[];
	
	public static void main(String[] args) {
		Dp = new int[10001];
		int max = 0;
		
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		Arr = new int[N+1];
		for(int i=1; i<=N; i++)
			Arr[i]=reader.nextInt();
		reader.close();
		
		// 계산
		for(int i=1; i<=N; i++) {
			int min = 0;
			for(int j=0; j<i; j++) {
				if (Arr[i]>Arr[j])
					min = Math.max(min, Dp[j]);
			}
	       		Dp[i] = min + 1;
	        	max = Math.max(max, Dp[i]);
		}
		System.out.println(max);
	}
}
