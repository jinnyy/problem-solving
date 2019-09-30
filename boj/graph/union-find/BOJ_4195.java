/*
 * [백준][4195] 친구 네트워크
 * 50476 KB	468 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.HashMap;


public class Main {
	static int parent[], size[], id;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			int F = Integer.parseInt(br.readLine());
			parent = new int[F+2];
			size = new int[F+2];
			for(int i=0; i<F+2; i++) {
				parent[i] = i;
				size[i] = 1;
			}
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			id = 0;
			for(int f=0; f<F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String k1 = st.nextToken();
				String k2 = st.nextToken();
				int f1 = getId(map, k1);
				int f2 = getId(map, k2);
				bw.append(String.valueOf(union(f1, f2)));
				bw.newLine();
			}
		}
		br.close();
		bw.close();
		
	}
	private static int getId(HashMap<String, Integer> map, String key) {
		if(!map.containsKey(key)) map.put(key, id++);
		return map.get(key);
	}
	
	static int union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if(p1 != p2) {
			parent[p2] = p1;
			size[p1] += size[p2];
		}
		return size[p1];
	}
	
	static int find(int v) {
		if(parent[v] == v) return v;
		return parent[v] = find(parent[v]);
	}
}
