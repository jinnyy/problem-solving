/*
 * [백준][5086번] 배수와 약수
 * 12988KB 72ms
 */
package etc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class MultipleAndFactor {
	static final String FACTOR = "factor";
	static final String MULTIPLE = "multiple";
	static final String NEITHER = "neither";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(A==B) break;
			
			if(A>B && A%B==0) bw.append(MULTIPLE);
			else if(B>A && B%A==0) bw.append(FACTOR);
			else bw.append(NEITHER);
			bw.newLine();
		}
		bw.flush();
	}
}
