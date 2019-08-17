/*
 * [ALGOSPOT][NUMB3RS] 두니발 박사의 탈옥
 * 296ms
 */
package dp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class NUMB3RS {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int C = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=C; test_case++) {
			int N, D, P, T, E[];
			boolean[][] map;
			double dp[][];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken()); // 시작점
			map = new boolean[N][N];
			E = new int[N];
			for(int i=0; i<N; i++) {
				int cnt = 0;
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = st.nextToken().equals("1") ? true : false;
					if(map[i][j]) cnt++;
				}
				E[i] = cnt;
			}
			dp = new double[D+1][N];
			dp[0][P] = 1; // 초기값
			
			
			//연산
			// dp[d][n] = d번째 날에 n번 마을에 있을 확률
			for(int day=1; day<=D; day++) {
				for(int n=0; n<N; n++) {
					for(int i=0; i<N; i++) {
						if(map[n][i])
							dp[day][n] += dp[day-1][i] / E[i];
					}
				}
			}
			
			// 출력
			T = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<T; i++) {
				sb.append(dp[D][Integer.parseInt(st.nextToken())]);
				sb.append(" ");
			}
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	
}
