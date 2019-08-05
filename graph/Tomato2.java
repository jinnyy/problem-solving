/*
 * [백준] 토마토
 * https://www.acmicpc.net/problem/7569
 */
package graph;
import java.util.Scanner;
import java.util.LinkedList;


public class Tomato2 {
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, 1, -1, 0, 0};
	static int[] dx = {1, -1, 0, 0, 0, 0};

	public static void main(String[] args) {
		int M, N, H, map[][][], cnt=0;
		boolean visited[][][];
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		LinkedList<Point> Q = new LinkedList<Point>();
		for(int h=0; h<H; h++) {
			for(int n=0; n<N; n++) {
				for(int m=0; m<M; m++) {
					map[h][n][m] = sc.nextInt();
					if(map[h][n][m]==1) {
						Q.add(new Point(h, n, m));
						visited[h][n][m] = true;
					}
					if(map[h][n][m]==0) cnt++;
				}
			}
		}
		sc.close();
		
		// 계산 시작
		int max = 1;
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			
			int min=Integer.MAX_VALUE;
			for(int i=0; i<6; i++) {
				int h, y, x;
				h = cur.h + dh[i];
				y = cur.y + dy[i];
				x = cur.x + dx[i];
				if(x<0 || y<0 || h<0 || h>=H || y>=N || x>=M || map[h][y][x]==-1) continue;
				if(!visited[h][y][x]) {
					Q.add(new Point(h, y, x));
					visited[h][y][x] = true;
					cnt--;
				}
				if(map[h][y][x] != 0)
					min = Math.min(min, map[h][y][x]);
			}
			if(map[cur.h][cur.y][cur.x]==1) continue;
			max = Math.max(max, map[cur.h][cur.y][cur.x] = min+1);
		}
		// 0들을 모두 방문하지 못했을 경우(남은 토마토가 있는 경우) -1
		if(cnt==0) System.out.println(max-1);
		else System.out.println(-1);
	}
}


class Point {
	int y, x, h;
	
	Point(int h, int y, int x) {
		this.h = h;
		this.y = y;
		this.x = x;
	}
	
	// for debugging
	public String toString() {
		return String.format("(%d, %d, %d)", h, y, x);
	}
}
