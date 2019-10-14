/*
 * [백준][15663] N과 M (9)
 * 20332 KB	144 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, num[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		Arrays.sort(num);
		
		select(0, new int[M], new boolean[N]);
		bw.close();
	}

	static void select(int depth, int[] seq, boolean[] visited) throws Exception {
		if(depth == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<M; i++) {
				sb.append(seq[i]);
				sb.append(" ");
			}
			bw.append(sb.toString());
			bw.newLine();
			return;
		}
		int prev = -1;
		for(int next=0; next<N; next++) {
			if(visited[next] || prev == num[next]) continue;
			seq[depth] = num[next];
			visited[next] = true;
			select(depth+1, seq, visited);
			visited[next] = false;
			prev = num[next];
		}
	}
}
