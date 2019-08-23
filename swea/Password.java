/*
 * [SWEA][5658] 보물상자 비밀번호
 * 18,552KB	115ms
 */
package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Collections;


public class Password {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			// read
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());	// 숫자의 개수
			int K = Integer.parseInt(st.nextToken());	// 크기 순서
			int n = N/4;
			String input = br.readLine().trim();
			
			// calculate
			PriorityQueue<String> pq = new PriorityQueue<String>(28, Collections.reverseOrder());
			// pq에 숫자들 넣고 K만큼 꺼냄 (중복시 K개에 포함하지 않음)
			for(int i=0; i<N-n; i++) {
				pq.add(input.substring(i, i+n));
			}
			// [N-i, N) + [0, n-i);
			for(int i=n; i>0; i--) {
				StringBuilder sb = new StringBuilder();
				sb.append(input.substring(N-i));
				sb.append(input.substring(0, n-i));
				pq.add(sb.toString());
			}
			String cur = "";
			int cnt = 0;
			while(cnt < K) {
				String next = pq.poll();
				if(next.equals(cur)) continue;	// 중복이면 넘김
				cur = next;
				cnt++;
			}
			
			// write
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(convert(cur));
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	private static int convert(String input) {
		int sum = 0;
		int digit = 1;
		for(int i=input.length()-1; i>=0; i--) {
			char cur = input.charAt(i);
			if(Character.isDigit(cur)) sum += (cur - '0') * digit;
			else sum += (cur - 'A' + 10) * digit;
			digit *= 16;
		}
		return sum;
	}
}
