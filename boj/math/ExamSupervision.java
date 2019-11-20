/*
 * [백준][13458] 시험 감독
 * 123936 KB	440 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception {
		// read
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		float B = Float.parseFloat(st.nextToken());
		float C = Float.parseFloat(st.nextToken());
		br.close();
		
		// calculate
		long result = 0;
		for(float student : A) {
			student -= B;
			result++;
			if(student > 0) result += Math.ceil(student/C);
		}
		
		// write
		System.out.print(result);
	}
}
