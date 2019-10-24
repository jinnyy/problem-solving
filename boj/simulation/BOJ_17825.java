/*
 * [백준][17825] 주사위 윷놀이
 * 105196 KB	280 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static final Map map = new Map();
	static int dice[], max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dice = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		max = 0;
		select(0, new int[10]);
		System.out.print(max);
	}
	
	static void select(int depth, int[] seq) {
		if(depth == 10) {
			Map map_cache = map;
			int sum = 0;
			int[] loc = new int[4];
			boolean[] visited = new boolean[Map.SIZE];
			for(int i=0; i<10; i++) {
				int hid = seq[i];
				Node h = map.nodes[loc[hid]];	// h 말이 달림
				if(h.id == Map.SIZE - 1) return;
				
				visited[h.id] = false;				
				h = h.next(dice[i]);
				if(h.id<Map.SIZE-1 && visited[h.id]) return;
				else {
					loc[hid] = h.id;
					visited[h.id] = true;
					sum += h.getScore();
				}
			}
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			seq[depth] = i;
			select(depth+1, seq);
		}
	}
}




class Map {
	static final int SIZE = 33;
	Node[] nodes;
	
	Map() {
		nodes = new Node[SIZE];
		nodes[0] = new Node(0, 0);
		for(int i=1; i<=20; i++) {
			nodes[i] = new Node(i, i*2);
			nodes[i-1].next[0] = nodes[i]; 
		}
		nodes[21] = new Node(21, 13);	// 왼쪽
		nodes[22] = new Node(22, 16);
		nodes[23] = new Node(23, 19);
		nodes[24] = new Node(24, 22);	// 아래
		nodes[25] = new Node(25, 24);
		nodes[26] = new Node(26, 28);	// 오른쪽
		nodes[27] = new Node(27, 27);
		nodes[28] = new Node(28, 26);
		nodes[29] = new Node(29, 25);	// 가운데
		nodes[30] = new Node(30, 30);	// 가운데 바로 위
		nodes[31] = new Node(31, 35);
		nodes[32] = new Node(32, 0);	// 마지막
		nodes[5].next[1] = nodes[21];
		nodes[10].next[1] = nodes[24];
		nodes[15].next[1] = nodes[26];
		// 왼
		nodes[21].next[0] = nodes[22];
		nodes[22].next[0] = nodes[23];
		nodes[23].next[0] = nodes[29];
		// 아래
		nodes[24].next[0] = nodes[25];
		nodes[25].next[0] = nodes[29];
		// 오
		nodes[26].next[0] = nodes[27];
		nodes[27].next[0] = nodes[28];
		nodes[28].next[0] = nodes[29];
		// 가운데부터 위로
		nodes[29].next[0] = nodes[30];
		nodes[30].next[0] = nodes[31];
		nodes[31].next[0] = nodes[20];
		// 마지막
		nodes[20].next[0] = nodes[32];
		nodes[32].next[0] = nodes[32];
	}
}

class Node {
	int id, score;
	Node[] next;
	
	Node(int id, int score) {
		this.id = id;
		this.score = score;
		this.next = new Node[2];
	}
	
	Node next(int len) {
		Node cur = this;
		if(next[1] != null) {
			cur = next[1];
			len--;
		}
		for(int i=0; i<len; i++) {
			cur = cur.next[0];
		}
		return cur;
	}
	
	int getScore() {
		return score;
	}
}
