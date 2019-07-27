package swea;
import java.util.Scanner;
import java.util.LinkedList;


public class Arrest {
	static final int[][] dy = {{}, {1,-1,0,0}, {1,-1}, {0,0}, {-1,0}, {1,0}, {0,1}, {0,-1}};
	static final int[][] dx = {{}, {0,0,1,-1}, {0,0}, {1,-1}, {0,1}, {0,1}, {-1,0}, {-1,0}};

	public static void main(String[] args) {
		int T, map[][], count=0, N, M, visitCnt[][];
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int R, C, L;
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			map = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			count = 1;
			visitCnt = new int[N][M];
			LinkedList<GridPoint> Q = new LinkedList<GridPoint>();
			Q.add(new GridPoint(R, C));
			visitCnt[R][C] = 1;
			while(!Q.isEmpty()) {
				GridPoint cur = Q.poll();
				int y, x;
				int min = Integer.MAX_VALUE;
				for(int i=0; i<dy[map[cur.y][cur.x]].length; i++) {
					y = cur.y + dy[map[cur.y][cur.x]][i];
					x = cur.x + dx[map[cur.y][cur.x]][i];
					if(x<0 || x>=M || y<0 || y>=N || map[y][x]==0 || visitCnt[y][x]>=L) continue;
					
					for(int j=0; j<dy[map[y][x]].length; j++) {
						int curDy = dy[map[cur.y][cur.x]][i];
						int curDx = dx[map[cur.y][cur.x]][i];
						int newDy = dy[map[y][x]][j];
						int newDx = dx[map[y][x]][j];
						if((curDy==0 && curDx + newDx == 0) || (curDx==0 && curDy + newDy == 0)) {
							if(visitCnt[y][x]==0) Q.add(new GridPoint(y, x));
							else if(visitCnt[y][x]!=0) min = Math.min(min, visitCnt[y][x]);
						}
					}
				}
				if(visitCnt[cur.y][cur.x]!=1) {
					if(min!=Integer.MAX_VALUE) {
						if(visitCnt[cur.y][cur.x]==0) count++;
						visitCnt[cur.y][cur.x] = min + 1;
					}
					else visitCnt[cur.y][cur.x] = min;
				}
			}
			System.out.println(String.format("#%d %d", test_case, count));
		}
		sc.close();
	}

}

class GridPoint {
	int y, x;
	
	GridPoint(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	public String toString() {
		return String.format("(%d, %d)", this.y, this.x);
	}
}
