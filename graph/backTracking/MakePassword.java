/*
 * [백준][4576번] 암호 만들기
 * 17744KB 100ms
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;


public class MakePassword {
	static int L, C;
	static String[] chars;
	static StringBuilder globalSb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		PriorityQueue<String> pq = new PriorityQueue<String>();
		for(int i=0; i<C; i++) {
			pq.add(st.nextToken());
		}
		chars = new String[C];
		for(int i=0; i<C; i++) {
			chars[i] = pq.poll();
		}
		
		// 연산
		dfs(0, -1, "", false, 0);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(globalSb.toString());
		bw.newLine();
		bw.flush();
	}
	
	static void dfs(int depth, int cur, String str, boolean hasVowel, int cons) {
		if(depth==L) {
			if(!hasVowel || cons<2) return;
			globalSb.append(str);
			globalSb.append("\n");
		}
		for(int i=cur+1; i<C; i++) {
			StringBuilder localSb = new StringBuilder();
			localSb.append(str);
			localSb.append(chars[i]);
			if(isVowel(chars[i].charAt(0)))
				dfs(depth+1, i, localSb.toString(), true, cons);
			else
				dfs(depth+1, i, localSb.toString(), hasVowel, cons+1);
		}
	}
	
	static boolean isVowel(char c) {
		if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u')
			return true;
		return false;
	}
}
