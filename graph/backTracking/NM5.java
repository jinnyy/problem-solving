/*
 * [백준][15654번] N과 M (5)
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;


public class NM5 {
	static int N, M, num[];
	static StringBuilder sb;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0; i<N; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = pq.poll();
		}
		print(0, new boolean[N], "");
		bw.flush();
	}

	static void print(int depth, boolean[] visited, String result) throws Exception {
		if(depth==M) {
			bw.write(result);
			bw.newLine();
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sb = new StringBuilder();
				sb.append(result);
				sb.append(num[i]);
				sb.append(" ");
				print(depth+1, visited, sb.toString());
				visited[i] = false;
			}
		}
	}
}
