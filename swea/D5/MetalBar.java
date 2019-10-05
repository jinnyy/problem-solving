/*
 * [SWEA][1259] 금속 막대
 * 18,552 kb	120 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.HashSet;


public class Solution {
	static final int MAX = 100;
	static Node[] nodes;
	static LinkedList<Integer> gPath;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			nodes = new Node[MAX+1];
			for(int i=0; i<=MAX; i++)
				nodes[i] = new Node();
			int[] size = new int[MAX+1];
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			HashSet<Integer> cand = new HashSet<Integer>();
			for(int i=0; i<N; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				nodes[from].adj.add(to);
				size[to]++;
				if(size[from] == 0) cand.add(from);
			}
			
			// 시작점은 하나로 한정 (모든 edge 한번씩 사용)
			int start = -1;
			for(int c : cand) {
				if(size[c] == 0) {
					start = c;
					break;
				}
			}
			gPath = null;
			dfs(start, new LinkedList<Integer>(), new boolean[MAX+1]);
			
			// write
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			for(int p : gPath) {
				sb.append(p);
				sb.append(" ");
			}
			bw.append(sb.toString());
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}
	
	static void dfs(int cid, LinkedList<Integer> path, boolean[] visited) {
		LinkedList<Integer> gPath_cache = gPath;
		if(gPath==null || gPath.size() < path.size()) {
			gPath = new LinkedList<Integer>();
			gPath.addAll(path);
		}
		
		Node cur = nodes[cid];
		for(Integer next : cur.adj) {
			if(!visited[next]) {
				visited[next] = true;
				path.add(cid);
				path.add(next);
				dfs(next, path, visited);
				path.poll();
				path.poll();
				visited[next] = false;
			}
		}
		
	}

}

class Node {
	LinkedList<Integer> adj;
	
	Node(){
		adj = new LinkedList<Integer>();
	}
	
}
