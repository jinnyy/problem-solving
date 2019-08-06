/*
 * [백준] 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 */
package graph;
import java.util.Scanner;
import java.util.LinkedList;


public class BreakingWallAndMoving {
	static final int WALL = -1;
	static final int[] dy = {0, 0, 1, -1};
	static final int[] dx = {1, -1, 0, 0};

	public static void main(String[] args) {
		int N, M, map[][];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		sc.nextLine();
		for(int i=0; i<N; i++) {
			String temp = sc.nextLine();
			for(int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j) - '0';
				if(map[i][j]==1) map[i][j] = WALL;
			}
		}
		sc.close();
		
		System.out.println(bfs(N, M, map));
	}
	
	static int bfs(int N, int M, int[][] map) {
		LinkedList<Point> Q = new LinkedList<Point>();
		Q.add(new Point(0, 0, 0));
		int[][][] dist = new int[N][M][2];
		dist[0][0][0] = 1;
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			if(cur.y==N-1 && cur.x==M-1) 
				return dist[cur.y][cur.x][cur.broke];
			
			for(int k=0; k<4; k++) {
				int y = cur.y + dy[k];
				int x = cur.x + dx[k];
				if(x<0 || y<0 || y>=N || x>=M) continue;
				if(dist[y][x][cur.broke]>0) continue;
				if(map[y][x]==0) {
					dist[y][x][cur.broke] = dist[cur.y][cur.x][cur.broke] + 1;
					Q.add(new Point(y, x, cur.broke));
				} else if(map[y][x]==WALL && cur.broke==0) {
					dist[y][x][1] = dist[cur.y][cur.x][cur.broke] + 1;
					Q.add(new Point(y, x, 1));
				}
			}
		}
		return -1;
	}
}

class Point {
	int y, x, broke;
	
	Point(int y, int x, int broke) {
		this.y = y;
		this.x = x;
		this.broke = broke;
	}
	
	// for debugging
	public String toString() {
		return String.format("(%d, %d)", this.y, this.x);
	}
}
