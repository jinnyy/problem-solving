/*
 * [백준] 전깃줄
 * https://www.acmicpc.net/problem/2565
 */
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int N, wire[], maxWire=0, dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		wire = new int[501];
		for(int i=0; i<N; i++) {
			int idx = sc.nextInt();
			maxWire = Math.max(maxWire, wire[idx] = sc.nextInt());
			maxWire = Math.max(maxWire, idx);
		}
		sc.close();
		dp = new int[501][2];
		
		// N - 가장 긴 증가 부분 수열 길이
		int minLength=0, maxLength=0;
		for(int i=1; i<=maxWire; i++) {
			if(wire[i]==0) continue;
			int locMaxCnt = 0;
			int locMinCnt = 0;
			for(int j=1; j<=i; j++) {
				if(wire[j] < wire[i]) locMaxCnt = Math.max(locMaxCnt, dp[j][0]);
				if(wire[j] < wire[i]) locMinCnt = Math.max(locMinCnt, dp[j][1]);
			}
			maxLength = Math.max(maxLength, dp[i][0] = locMaxCnt + 1);
			minLength = Math.max(minLength, dp[i][1] = locMinCnt + 1);
		}
		// print
		System.out.println(N - Math.max(maxLength, minLength));
	}
}
