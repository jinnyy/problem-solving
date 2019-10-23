/*
 * [백준][14501] 퇴사
 * 12972 KB	72 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception {
		int N, T[], P[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N+1];
		P = new int[N+1];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		for(int i=N-1; i>=0; i--) {
			dp[i] = dp[i+1];
			if(i + T[i] <= N) {
				dp[i] = Math.max(dp[i], dp[i+T[i]] + P[i]);
			}
		}
		
		System.out.print(dp[0]);
	}

}
