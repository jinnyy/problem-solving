/*
 * [백준] 줄세우기
 */
import java.util.Scanner;



public class Main {
	public static void main(String[] args) {
		int N, num[], dp[], Max;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		for(int i=0; i<N; i++)
			num[i] = sc.nextInt();
		sc.close();
		
		dp = new int[201];
		Max = 0;
		/*
		 * 증가하는 부분 수열의 길이를 찾은 뒤(MAX),
		 * 수열의 길이(N)에서 뺀다.
		 */
		for(int i=0; i<N; i++) {
			int max = 0;
			for(int j=0; j<=i; j++) {
				if (num[j] < num[i]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
			Max = Math.max(dp[i], Max);
		}
		System.out.println(N-Max);
	}

}
