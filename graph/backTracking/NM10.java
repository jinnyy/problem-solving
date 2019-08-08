/*
 * [백준][15664번] N과 M (10)
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class NM10 {
	static int N, M, num[];
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
		int idx = 0;
		while(!pq.isEmpty()) {
			num[idx] = pq.poll();
			idx++;
		}
		printSeq(0, -1, new int[M+1]);
		bw.flush();
	}
	
	static void printSeq(int depth, int cur, int[] seq) throws Exception {
		if(depth==M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<M-1; i++) {
				sb.append(seq[i]);
				sb.append(" ");
			}
			sb.append(seq[M-1]);
			bw.write(sb.toString());
			bw.newLine();
			return;
		}
		int prev = -1;
		for(int i=cur+1; i<N; i++) {
			if(prev != num[i]) {
				seq[depth] = num[i];
				printSeq(depth+1, i, seq);
				prev = num[i];
				seq[depth] = 0;
			}
		}
	}
}
