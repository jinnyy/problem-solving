package dfs_bfs;
/*
 * [백준] 연결요소의 개수
 * https://www.acmicpc.net/problem/11724
 */
import java.util.Scanner;
import java.util.ArrayList;


public class ConnectedComponents {
	static int N, M, count;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		M = reader.nextInt();
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<N+1; i++)
			graph.add(new ArrayList<Integer>());
		int x, y;
		for(int i=0; i<M; i++) {
			x = reader.nextInt();
			y = reader.nextInt();
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		reader.close();
		visited = new boolean[N+1];
		
    // dfs 실행
		dfs();
		System.out.println(count);
	}
	
	private static void dfs() {
		for(int i=0; i<=N; i++)
			visited[i]=false;
		count = 0;
		for(int i=1; i<=N; i++) {
      // 새로운 노드에서 탐색을 시작할 때마다 count를 1씩 증가
			if(!visited[i]) {
				count++;
				doDfs(i);
			}
		}
	}
	
	private static void doDfs(int x) {
		visited[x] = true;
		for(int y: graph.get(x)) {
			if(!visited[y]) doDfs(y);
		}
	}

}
