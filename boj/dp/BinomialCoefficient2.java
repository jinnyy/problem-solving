/*
 * [백준] 이항 계수 2
 * https://www.acmicpc.net/problem/11051
 */
import java.util.Scanner;


public class Main {
	static final int MOD = 10007;

	public static void main(String[] args) {
		int N, K, pascal[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sc.close();
		
		pascal = new int[1001][1001];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=i; j++) {
				if(j>i/2)
					pascal[i][j] = pascal[i][i-j];
				else {
					if(j==0) pascal[i][j] = 1;
					else pascal[i][j] = (pascal[i-1][j-1] + pascal[i-1][j]) % MOD;
				}
			}
		}
		
		System.out.println(pascal[N][K]);
	}

}
