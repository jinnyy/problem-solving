package graph;
/*
 * [백준] 나이트의 이동
 * https://www.acmicpc.net/problem/7562
 */
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;


public class Knight {
	static int N, I, Count, curX, curY, goalX, goalY, map[][];
	static Queue<KnightPoint> Q;
	static final int[] dX = {-2, -2, -1, -1, 1,  1, 2,  2};
	static final int[] dY = { 1, -1,  2, -2, 2, -2, 1, -1};
	

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		for(int i=0; i<N; i++) {
			I = reader.nextInt();
			curX = reader.nextInt();
			curY = reader.nextInt();
			goalX = reader.nextInt();
			goalY = reader.nextInt();
						
			// 계산
			bfs();
			System.out.println(map[goalY][goalX]);
		}
		reader.close();
	}
	
	private static void bfs() {
		map = new int[I][I];  // default 0
		// 예외처리
		if(curX==goalX && curY==goalY) return;
		// 초기화
		Q = new LinkedList<KnightPoint>();
		Q.add(new KnightPoint(curY, curX));
		
		KnightPoint cur;
		while(Q.size() != 0) {
			cur = Q.poll();
			
			int x, y;
			for(int i=0; i<dX.length; i++) {
				x = cur.x + dX[i];
				y = cur.y + dY[i];
				if (x<0 || x>=I || y<0 || y>=I) continue;
				if (map[y][x]==0) {
					map[y][x] = map[cur.y][cur.x]+1;
					Q.add(new KnightPoint(y, x));
				}
				if (x==goalX && y==goalY) return; // 목표지점에 도달하면 끝냄
			}
		}
	}
}


// cf. 패키지 안에 Point 클래스가 있어서 KnightPoint라고 이름을 만들었지만
// 단순히 점을 의미하는 것이므로 Point로 하고자했다..
class KnightPoint {
	int x, y;
	
	KnightPoint(int y, int x) {
		this.x = x;
		this.y = y;
	}
}
