/*
 * [algospot][POLY] 폴리노미오
 * 124ms
 */
package dp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class POLY {
	static final int MOD = 10000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int C, dp[][];
		C = Integer.parseInt(br.readLine());
		dp = new int[101][101];
		for(int c=0; c<C; c++) {
			int N = Integer.parseInt(br.readLine());
			// 연산
			for(int n=1; n<=N; n++) {
				dp[n][n] = 1;
				for(int first=1; first<n; first++) {
					if(dp[n][first]!=0) continue; // 캐시
					for(int second=1; second<=n-first; second++) {
						dp[n][first] += (second+first-1) * dp[n-first][second]; // 점화식
						dp[n][first] %= MOD;
					}
				}
			}
			// 출력
			int result = 0;
			for(int i=0; i<=N; i++) {
				result += dp[N][i];
				result %= MOD;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(result);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}
