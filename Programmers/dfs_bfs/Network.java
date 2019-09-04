/*
 * [프로그래머스][dfs/bfs][level3] 네트워크
 * https://programmers.co.kr/learn/courses/30/lessons/43162
 */
package dfs_bfs;


public class Network {
	static int cnt, N;
	
	// dfs로 연결 요소의 개수를 찾는다.
	public int solution(int n, int[][] computers) {
		cnt = 0;
		N = n;
		boolean[] visited = new boolean[n];
		for(int i=0; i<N; i++) {
			// 새로운 노드에서 시작할 때 cnt를 1씩 증가시킨다.
			if(!visited[i]) {
				dfs(computers, visited, i);
				cnt++;
			}
		}
        return cnt;
    }
	
	private static void dfs(int[][] computers, boolean[] visited, int cur) {
		visited[cur] = true;
		for(int i=0; i<N; i++) {
			if(!visited[i] && computers[cur][i]==1 && i!=cur) {
				dfs(computers, visited, i);
			}
		}
	}
}
