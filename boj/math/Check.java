/*
 * [백준][2981] 검문
 * 12956KB	76ms
 */
package etc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Check {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> numbers = new PriorityQueue<Integer>();
		for(int i=0; i<N; i++) {
			numbers.add(Integer.parseInt(br.readLine()));
		}
		br.close();
		
		
		int prev = numbers.poll();
		int cur = numbers.poll();
		int G = cur - prev;
		while(!numbers.isEmpty()) {
			prev = cur;
			cur = numbers.poll();
			G = gcd(G, cur-prev);
		}
		PriorityQueue<Integer> pq = dividers(G);
		pq.add(G);
		
		
		StringBuilder sb = new StringBuilder();
		prev = -1;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(cur==prev) continue;
			sb.append(cur);
			sb.append(" ");
			prev = cur;
		}
		System.out.print(sb.toString());
	}
	
	private static int gcd(int A, int B) {
		return B==0? A : gcd(B, A%B);
	}
	
	private static PriorityQueue<Integer> dividers(int num) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=2; i*i<=num; i++) {
			if(num%i==0) {
				pq.add(i);
				pq.add(num/i);
			}
		}
		return pq;
	}
}
