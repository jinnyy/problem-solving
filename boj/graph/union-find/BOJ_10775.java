/*
 * [백준][10775] 공항
 * 22732 KB	200 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {
	static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		int cnt = 0;
		parent = new int[G+1];
		for(int i=1; i<=G; i++) {
			parent[i] = i;
		}
		
		for(int i=0; i<P; i++) {
			int max = Integer.parseInt(br.readLine());
			int gate = find(max);
			if(gate == 0) {
				break;
			} else {
				union(gate, gate - 1);
				cnt++;
			}
		}
		bw.append(String.valueOf(cnt));
		br.close();
		bw.close();
	}
	
	static void union(int v1, int v2) {
		parent[find(v1)] = find(v2);
	}
	
	static int find(int v) {
		if(v == parent[v]) return v;
		return parent[v] = find(parent[v]);
	}

}
