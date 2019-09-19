/*
 * [백준][17298] 오큰수
 * 198372 KB	968 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws Exception {
		// read
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		// calculate
		int[] result = new int[N];
		Stack<Integer> S = new Stack<Integer>();
		for(int i=N-1; i>=0; i--) {
			int cur = A[i];
			while(S.size() != 0 && result[i] == 0) {
				if(cur < S.peek()) result[i] = S.peek();
				else S.pop();
			}
			if(result[i] == 0) result[i]=-1;
			S.push(cur);
		}
		// write
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(result[i]);
			sb.append(" ");
		}
		System.out.print(sb.toString());
	}
}
