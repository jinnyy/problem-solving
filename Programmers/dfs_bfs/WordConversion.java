/*
 * [프로그래머스][dfs/bfs][level3] 단어 변환
 * https://programmers.co.kr/learn/courses/30/lessons/43163
 */
package dfs_bfs;
import java.util.Iterator;
import java.util.LinkedList;


public class WordConversion {
	static Graph graph;
	
	public static int solution(String begin, String target, String[] words) {
		// 1. 단어 간 거리 계산 -> 그래프 생성 (인접리스트)
		// 2. bfs로 최단거리(길이)  탐색
		String[] words2 = new String[words.length+1];
		words2[0] = begin;
		int t = -1;
		for(int i=0; i<words.length; i++) {
			words2[i+1] = words[i];
			if(words[i].equals(target)) t = i;
		}
		graph = new Graph(words2);
		Node tNode;
		if(t>0) tNode = graph.nodes[t+1];
		else return 0;
		
		// bfs 시작
		LinkedList<Node> Q = new LinkedList<Node>();
		Q = new LinkedList<Node>();
		Q.add(graph.nodes[0]);
		graph.nodes[0].visited = true;
		while(!Q.isEmpty()) {
			Node cur = Q.pop();
			// 인접 리스트 검사
			Iterator<Node> it = cur.adj.iterator();
			int depth = Integer.MAX_VALUE;
			while(it.hasNext()) {
				Node node = it.next();
				if (node.depth >= 0)
					depth = Math.min(depth, node.depth);
				if (!node.visited) {
					Q.add(node);
					node.visited = true;
				}
			}
			cur.depth = depth==Integer.MAX_VALUE? 0: depth + 1;
			if(cur.word.equals(target))
				break;
		}
		return tNode.depth<0? 0: tNode.depth;
    }
	
}


// 새로운 클래스
class Graph {
	Node[] nodes;
	int size;
	
	Graph() {
		this.size = 0;
		this.nodes = new Node[51];
	}
	// 인접행렬 만듦
	Graph(String[] words) {
		this.size = words.length;
		this.nodes = new Node[this.size];
		for(int i=0; i<this.size; i++) {
			this.nodes[i] = new Node(words[i]);
		}
		
		int wordLength = words[0].length();
		for(int i=0; i<this.size; i++) {
			Node cur = this.nodes[i];
			for(int j=0; j<this.size; j++) {
				if(i==j) continue;
				int diff = 0;
				for(int k=0; k<wordLength; k++) {
					if(words[i].charAt(k) != words[j].charAt(k))
						diff++;
				}
				if(diff==1) {
					cur.adj.add(this.nodes[j]);
				}
			}
		}
	}
}

class Node {
	String word;
	LinkedList<Node> adj;
	boolean visited;
	int depth;
	
	Node(String word) {
		this.word = word;
		this.adj = new LinkedList<Node>();
		this.visited = false;
		this.depth = -1;
	}
	
	// for debugging
	public String toString() {
		return this.word;
	}
}
