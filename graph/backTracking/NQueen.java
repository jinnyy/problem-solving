/*
 * [백준][9663번] N-Queen
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class NQueen {
	static int N, cnt;
	
	public static void main(String[] args) throws Exception {
		solve();
	}
	
	static void solve() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		// 연산
		cnt = 0;
		for(int i=0; i<N; i++) {
			int[] path = new int[N+1];
			path[0] = i;
			dfs(1, path);
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(Integer.toString(cnt));
		bw.newLine();
		bw.flush();
	}
	
	static void dfs(int depth, int[] path) {
		if(depth==N) {
			cnt++;
			return;
		}
		for(int x=0; x<N; x++) {
			path[depth] = x;
			if(!isOnPath(depth, path)) {
				dfs(depth+1, path);
			}
		}
	}
	
	static boolean isOnPath(int depth, int[] path) {
		for(int i=0; i<depth; i++) {
			if(path[i]==path[depth] || i==depth) return true;
			if(Math.abs(((float)depth-(float)i)) == Math.abs((float)path[depth]-(float)path[i])) return true;
		}
		return false;
	}
}
