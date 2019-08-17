/*
 * [ALGOSPOT][PACKING] 여행 짐 싸기
 * 1212ms / 2000ms
 */
package dp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class PACKING {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int C = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=C; test_case++) {
			int N, W;
			Item[] items;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			items = new Item[N+1];
			for(int i=1; i<=N; i++) {
				items[i] = new Item(br.readLine());
			}
			
			// dp[n][w] = n번째 물건까지 선택했을 때 무게가 w 이하인 경우 절박도의 최댓값
			// dp[n][w] = max( dp[n-1][w], dp[n-1][w-지금무게] + items[n] )
			boolean[][][] paths = new boolean[N+1][W+1][N+1];
			int[][] dp = new int[N+1][W+1];
			for(int n=1; n<=N; n++) {
				Item cur = items[n];
				for(int w=0; w<=W; w++) {
					if(w - cur.weight >= 0 && dp[n-1][w] < dp[n-1][w-cur.weight] + cur.importance) {
						for(int i=0; i<N; i++) {
							paths[n][w][i] = paths[n-1][w-cur.weight][i];
						}
						paths[n][w][n] = true;
						dp[n][w] = dp[n-1][w-cur.weight] + cur.importance;
					}
					else {
						dp[n][w] = dp[n-1][w];
						for(int i=0; i<N; i++) {
							paths[n][w][i] = paths[n-1][w][i];
						}
					}
				}
			}
			int size = 0;
			LinkedList<Integer> path = new LinkedList<Integer>();
			for(int i=0; i<=N; i++) {
				if(paths[N][W][i]) {
					size++;
					path.add(i);
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append(dp[N][W]);
			sb.append(" ");
			sb.append(size);
			bw.append(sb.toString());
			for(int i : path) {
				bw.newLine();
				bw.append(items[i].name);
			}
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}

}

class Item {
	String name;
	int weight, importance;
	
	Item(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");
		this.name = st.nextToken();
		this.weight = Integer.parseInt(st.nextToken());
		this.importance = Integer.parseInt(st.nextToken());
	}
	Item(String name, int weight, int importance){
		this.name = name;
		this.weight = weight;
		this.importance = importance;
	}
	
	public String toString() {
		return this.name;
	}
}
