/*
 * [백준][12852번] 1로 만들기 2
 */
package dp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class MakeOne2 {
	static int N;
	static StringBuilder sb;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		
		// calculation
		int[] dp = new int[N+1];
		String[] path = new String[N+1];
		path[1] = "1";
		for(int i=2; i<=N; i++) {
			StringBuilder sb = new StringBuilder();
			String past;
			dp[i] = dp[i-1] + 1;
			past = path[i-1];
			if(i%3==0 && dp[i] > dp[i/3]+1) {
				dp[i] = dp[i/3] + 1;
				past = path[i/3];
			}
			if(i%2==0 && dp[i] > dp[i/2]+1) {
				dp[i] = dp[i/2] + 1;
				past = path[i/2];
			}
			sb.append(i);
			sb.append(" ");
			sb.append(past);
			path[i] = sb.toString();
		}
		
		// print
		bw.write(String.valueOf(dp[N]));
		bw.newLine();
		bw.write(path[N]);
		bw.flush();
	}

}
