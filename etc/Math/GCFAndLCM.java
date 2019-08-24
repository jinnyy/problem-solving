/*
 * [백준][2609] 최대공약수와 최소공배수
 * 12904KB	72ms
 */
package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GCFAndLCM {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		br.close();
		
		int G = gcd(A, B);
		
		System.out.println(G);
		System.out.println(A*B/G);
	}
	
	// 유클리드 호제법
	// A와 B의 최대공약수 G는 A%B의 약수이다.
	private static int gcd(int A, int B) {
		if(B==0) return A;
		return gcd(B, A%B);
	}

}
