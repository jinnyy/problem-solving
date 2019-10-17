/*
 * [백준][1339] 단어 수학
 * 12992 KB	76 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Main {
	static final char START = 'A';

	public static void main(String[] args) throws Exception {
		int N;
		String words[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		Alphabet[] A = new Alphabet[26];
		for(int i=0; i<26; i++) {
			A[i] = new Alphabet(0, (char) (START+i));
		}
		for(int i=0; i<N; i++) {
			words[i] = br.readLine().trim();
			int L = words[i].length();
			int digit = 1;
			for(int c=L-1; c>=0; c--) {
				A[words[i].charAt(c)-START].idx += digit;
				digit *= 10;
			}
		}
		br.close();
		
		PriorityQueue<Alphabet> Q = new PriorityQueue<Alphabet>();
		for(Alphabet a : A) {
			if(a.idx == 0) continue;
			Q.add(a);
		}
		
		int[] visited = new int[26];
		int loc = 9;
		while(!Q.isEmpty() && loc>0) {
			Alphabet a = Q.poll();
			if(visited[a.value - START]>0) continue;
			visited[a.value - START] = loc--;
		}
		
		int sum = 0;
		for(String word : words) {
			int digit = 1;
			for(int i=word.length()-1; i>=0; i--) {
				sum += visited[word.charAt(i) - START] * digit;
				digit *= 10;
			}
		}
		System.out.print(sum);
	}

}


class Alphabet implements Comparable<Alphabet> {
	char value;
	int idx;
	
	Alphabet(int idx, char value) {
		this.idx = idx;
		this.value = value;
	}

	@Override
	public int compareTo(Alphabet a) {
		return a.idx - this.idx;
	}	
}
