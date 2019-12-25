/*
 * [백준] 점프점프
 * 11060
 */
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int N, A[], dp[];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N+1];
		for(int i=0; i<N; i++)
			A[i] = sc.nextInt();
		sc.close();
		
		dp = new int[N+1];
		for(int i=1; i<N; i++)
			dp[i] = -1;
		
		for(int i=0; i<N; i++) {
			// dp[i]: i번째까지 도착하기 위한 최소 점프 횟수
			if(i!=0 && dp[i]==-1) continue;
			// j칸 뒤의 dp 값을 계산한다.
			// j는 현재 A 값을 바라보고 있는 i번째보다 1칸 뒤에서부터 A[i]칸 뒤까지
			for(int j=i+1; j<=i+A[i]; j++) {
				// 새로운 dp 값이 더 작다면 업데이트한다.
				if(j<N && (dp[j]==-1 || dp[j]>dp[i]+1))
					dp[j] = dp[i]+1;
			}
		}
		System.out.println(dp[N-1]);
	}
}
