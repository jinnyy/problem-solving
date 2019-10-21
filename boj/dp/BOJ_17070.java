/*
 * [백준][17070] 파이프 옮기기 1
 * 13140 KB	76 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws Exception {
		int N, map[][], dp[][][];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		dp = new int[N+1][N+1][3];
		dp[1][2][0] = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j]==0) {
					dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
				}
				if(map[i][j]==0) {
					dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
				}
				if(map[i][j]==0 && map[i-1][j]==0 && map[i][j-1]==0) {
					dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				}
			}
		}
		
		int sum = 0;
		for(int i=0; i<3; i++)
			sum += dp[N][N][i];
		System.out.print(sum);
	}

}
