/*
 * [프로그래머스][level5][카카오 공개채용 2018] 길 찾기 게임
 */
import java.util.PriorityQueue;


class FindingPathGame {
	static int path[], idx;
	
    public int[][] solution(int[][] nodeinfo) {
    	PriorityQueue<Node> pq = new PriorityQueue<Node>();
    	int name = 1;
    	for(int[] node : nodeinfo) {
    		pq.add(new Node(node, name++));
    	}
    	int N = nodeinfo.length;
    	Tree tree = new Tree(pq.poll());
    	
    	
    	while(!pq.isEmpty()) {
    		tree.insert(pq.poll());
    	}
    	
    	// traversal
    	int[][] answer = new int[2][N];
    	path = new int[N];
    	idx = 0;
    	preorder(tree.root);
    	answer[0] = path;
    	// postorder
    	path = new int[N];
    	idx = 0;
    	postorder(tree.root);
    	answer[1] = path;
    	return answer;
    }
    
    static void preorder(Node cur) {
    	path[idx++] = cur.name;
    	if(cur.left != null) preorder(cur.left);
	    if(cur.right != null) preorder(cur.right);
    }
    
    static void postorder(Node cur) {
    	if(cur.left != null) postorder(cur.left);
	    if(cur.right != null) postorder(cur.right);
    	path[idx++] = cur.name;
    }
    
    
    static int findLoc(Node target, Node[] tree) {
    	int cur = 1;
    	while(tree[cur] != null) {
    		// target.x가 더 작으면 왼쪽 크면 오른쪽
    		if(target.x < tree[cur].x) {
    			cur = 2 * cur;
    		}
    		else {
    			cur = 2 * cur + 1;
    		}
    	}
    	return cur;
    }
}

class Tree {
	Node root;
	
	Tree(Node root) {
		this.root = root;
	}
	
	void insert(Node node) {
		Node cur = this.root;
		while(cur.left!=null || cur.right!=null) {
			if(node.x < cur.x) {
				if(cur.left == null) break;
				cur = cur.left;
			}
			else {
				if(cur.right == null) break;
				cur = cur.right;
			}
		}
		
		if(node.x < cur.x) cur.left = node;
		else cur.right = node;
	}
}


class Node implements Comparable<Node> {
	int y, x, name;
	Node left, right;
	
	Node(int[] input, int name) {
		this.x = input[0];
		this.y = input[1];
		this.name = name;
	}
	
	public int compareTo(Node n) {
		if(this.y==n.y) return this.x - n.x;
		return n.y - this.y;
	}
	
	public String toString() {
		return String.valueOf(name);
//		return String.format("(%d %d)", y, x);
	}
}
