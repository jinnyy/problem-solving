/*
 * [백준][15652번] N과 M (4)
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class NM4 {
	static int N, M;
	static StringBuilder sb;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int start=1; start<=N; start++)
			print(1, start, Integer.toString(start));
		bw.flush();
	}

	
	static void print(int depth, int cur, String res) throws Exception {
		if(depth==M) {
			bw.write(res);
			bw.newLine();
			return;
		}
		for(int next=cur; next<=N; next++) {
			sb = new StringBuilder();
			sb.append(res);
			sb.append(" ");
			sb.append(next);
			print(depth+1, next, sb.toString());
		}
	}
}
