/*
 * [백준][7579] 앱
 * 17492 KB	112 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static final int MAX = 10001;

	public static void main(String[] args) throws Exception {
		int N, M, memory[], cost[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memory = new int[N+1];
		cost = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++)
			memory[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++)
			cost[i] = Integer.parseInt(st.nextToken());
		br.close();
		
		int[][] dp = new int[N+1][MAX];
		for(int i=1; i<=N; i++) {
			for(int c=0; c<MAX; c++) {
				// 선택한다, 안한다
				dp[i][c] = Math.max(dp[i][c], dp[i-1][c]);
				// 선택할 경우: 원래 있던 값 vs 새로운 값
				if(c + cost[i] >= MAX) continue;
				dp[i][c + cost[i]] = Math.max(dp[i-1][c+cost[i]], dp[i-1][c] + memory[i]);
			}
		}
		
		// get answer
		int min = -1;
		for(int i=0; i<MAX; i++) {
			if(dp[N][i]>=M) {
				min = i;
				break;
			}
		}
		System.out.print(min);
	}

}
