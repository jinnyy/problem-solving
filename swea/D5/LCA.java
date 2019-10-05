/*
 * [SWEA][1248] 공통 조상
 * 28,280 KB	156 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.HashSet;


public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			LCANode[] nodes = new LCANode[V+1];
			for(int i=1; i<=V; i++) {
				nodes[i] = new LCANode(i);
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<E; i++) {
				int pid = Integer.parseInt(st.nextToken());
				int cid = Integer.parseInt(st.nextToken());
				nodes[cid].parent = nodes[pid];
				nodes[pid].addChild(nodes[cid]);
			}
			
			HashSet<LCANode> parents = new HashSet<LCANode>();
			LCANode cur = nodes[from];
			while(cur != null) {
				parents.add(cur);
				cur = cur.parent;
			}
			cur = nodes[to];
			int ancestor = -1;
			while(cur != null) {
				int pSize = parents.size();
				parents.add(cur);
				if(parents.size() == pSize) {
					ancestor = cur.id;
					break;
				}
				cur = cur.parent;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(ancestor);
			sb.append(" ");
			sb.append(nodes[ancestor].getSize());
			bw.append(sb.toString());
			bw.newLine();
		}
		
		br.close();
		bw.close();
	}

}

class LCANode {
	int id;
	LCANode parent, children[];
	
	LCANode(int name){
		this.id = name;
		this.children = new LCANode[2];
		this.parent = null;
	}
	
	void addChild(LCANode c) {
		if(children[0] == null) children[0] = c;
		else children[1] = c;
	}
	
	int getSize() {
		int size = 1;
		for(LCANode child : children) {
			if(child == null) break; 
			size += child.getSize();
		}
		return size;
	}
	
	public String toString() {
 		if(parent == null) return String.format("(id=%d [root])", id);
		return String.format("(id=%d par=%d)", id, parent.id);
	}
}
