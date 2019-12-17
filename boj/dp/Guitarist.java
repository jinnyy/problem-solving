/*
 * [백준] 기타리스트
 * https://www.acmicpc.net/problem/1495
 */
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		int N, S, M, V[];
		boolean dp[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		M = sc.nextInt();
		V = new int[N];
		for(int i=0; i<N; i++)
			V[i] = sc.nextInt();
		sc.close();
		
		/* 
		 * dp[i][j]: i번째 곡에서 볼륨 크기를 j로 할 수 있는지 여부
		 * 매 loop마다 볼륨 조절이 가능한지 여부를 체크하고
		 * 마지막에 최댓값을 구한다.
		 */
		
		dp = new boolean[N][M+1];
		if(S+V[0]<=M) dp[0][S+V[0]]=true;
		if(S-V[0]>=0) dp[0][S-V[0]]=true;
		// start calculation
		for(int i=1; i<N; i++) {
			boolean impossible = true;
			for(int j=0; j<=M; j++) {
				if(!dp[i-1][j]) {
					continue;
				}
				if(j+V[i]<=M) {
					dp[i][j+V[i]] = true;
					impossible = false;
				}
				if(j-V[i]>=0) {
					dp[i][j-V[i]] = true;
					impossible = false;
				}
			}
			if(impossible) {
				N = i+1;
				break;
			}
		}
		
		// get the answer
		int max = -1;
		for(int i=0; i<=M; i++) {
			if(dp[N-1][i]) max = i;
		}
		System.out.println(max);
	}

}
