package dp;
import java.util.Scanner;


public class IntTriangle {
	static int N, tri[][], Dp[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		tri = new int[N][N];
		for(int i=1; i<=N; i++) {
			for(int j=0; j<i; j++)
				tri[i-1][j] = sc.nextInt();
		}
		sc.close();
		
		
		// 계산
		int max = 0;
		Dp = new int[N][N];
		for(int j=0; j<N; j++) {
			Dp[N-1][j] = tri[N-1][j];
		}
		for(int i=N-2; i>=0; i--) {
			for(int j=0; j<i+1; j++) {
				Dp[i][j] = Math.max(Dp[i+1][j], Dp[i+1][j+1]) + tri[i][j];
				max = Math.max(max, Dp[i][j]);
			}
		}
		
		System.out.println(max);
	}
}
