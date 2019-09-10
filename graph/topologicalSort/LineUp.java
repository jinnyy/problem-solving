/*
 * [백준][2252] 줄세우기
 * 52180 KB	472 ms
 */
package topologicalSort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class LineUp {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<LinkedList<Integer>> graph = new ArrayList<LinkedList<Integer>>();
		for(int i=0; i<=N; i++) {
			graph.add(new LinkedList<Integer>());
		}
		int[] cnt = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			cnt[B]++;
		}
		br.close();
		
		LinkedList<Integer> Q = new LinkedList<Integer>();
		for(int i=1; i<=N; i++) {
			if(cnt[i]==0) Q.add(i);
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			bw.append(String.valueOf(cur));
			bw.append(" ");
			
			for(int next : graph.get(cur)) {
				cnt[next]--;
				if(cnt[next] == 0) {
					Q.add(next);
				}
			}
		}
		bw.close();
	}
}
