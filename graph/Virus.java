/*
 * [백준] 바이러스
 * https://www.acmicpc.net/problem/2606
 */
package graph;
import java.util.Scanner;
import java.util.LinkedList;


public class Virus {
	public static void main(String[] args) {
		int V, E;
		boolean visited[], connected[][];
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		connected = new boolean[V+1][V+1];
		for(int i=0; i<E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			connected[a][b] = true;
			connected[b][a] = true;
		}
		sc.close();
		visited = new boolean[V+1];
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.add(1);
		int cnt = 0;
		visited[1] = true;
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			for(int next=1; next<=V; next++) {
				if(connected[cur][next] && !visited[next]) {
					visited[next] = true;
					Q.add(next);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
