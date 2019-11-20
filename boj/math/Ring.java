/*
 * [백준][3036] 링
 * 13120 KB	80 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int first = Integer.parseInt(st.nextToken());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1; i<N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			int G = gcd(first, cur);
			// write
			StringBuilder sb = new StringBuilder();
			sb.append(first/G);
			sb.append("/");
			sb.append(cur/G);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	private static int gcd(int A, int B) {
		if(B==0) return A;
		return gcd(B, A%B);
	}

}
