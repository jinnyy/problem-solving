/*
 * [백준][15683] 감시
 * 32472 KB	300 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class Main {
	static final int WALL = -1;
	static final int MAX = 65;
	static final int[] dy = {0, -1, 0, 1};
	static final int[] dx = {-1, 0, 1, 0};
	static int N, M, map[][], C, min;
	static ArrayList<Camera> cameras;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cameras = new ArrayList<Camera>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(cur == 6) cur = WALL;
				else if(cur == 0) cur = 0;
				else {
					cameras.add(new Camera(i, j, cur));
					if(cur == 1) cur = 2;			// [1] - - 우 -
					else if(cur == 2) cur = 10;		// [2] 좌 - 우 -
					else if(cur == 3) cur = 6;		// [3] - 상 우 -
					else if(cur == 4) cur = 14;		// [4] 좌 상 우 -
					else if(cur == 5) cur = 15;		// [5] 좌 상 우 하
					cameras.get(cameras.size()-1).dir = cur;
				}
				map[i][j] = cur;
			}
		}
		br.close();
		C = cameras.size();
		
		min = MAX;
		select(0, new int[C]);
		System.out.print(min);
	}
	
	// 0번 cctv --> N-1번 cctv까지 (각각 방향 선택)
	static void select(int depth, int[] dirs) {
		if(depth == C) {
			boolean[][] visited = visit(dirs);
			min = Math.min(min, count(visited));
			return;
		}
		
		// 방향 선택
		Camera cur = cameras.get(depth);
		if(cur.type == 5) {
			dirs[depth] = cur.dir;
			select(depth+1, dirs);
		} else if(cur.type == 2) {
			// 방향 2개
			dirs[depth] = cur.dir;
			select(depth+1, dirs);
			dirs[depth] = rotate(cur.dir);
			select(depth+1, dirs);
			dirs[depth] = 0;
		} else {
			int ndir = cur.dir;
			for(int i=0; i<4; i++) {
				dirs[depth] = ndir;
				select(depth+1, dirs);
				dirs[depth] = 0;
				ndir = rotate(ndir);
			}
		}
	}
	
	private static int count(boolean[][] visited) {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j]==0)
					cnt++;
			}
		}
		return cnt;
	}
	
	private static boolean[][] visit(int[] dirs) {
		boolean[][] visited = new boolean[N][M];
		for(int c=0; c<C; c++) {
			Camera cam = cameras.get(c);
			int dir = dirs[c];
			// 모든 방향 방문
			for(int i=0; i<4; i++) {
				if(dir%2 == 1) {
					int cy = cam.y;
					int cx = cam.x;
					while(cy>=0 && cx>=0 && cy<N && cx<M) {
						if(map[cy][cx]==WALL) break;
						visited[cy][cx] = true;
						cy += dy[i];
						cx += dx[i];
					}
				}
				dir = dir >> 1;
			}
		}
		return visited;
	}
	
	
	private static int rotate(int dir) {
		return (dir << 1) % 16 + dir/8;
	}

}


class Camera {
	int y, x, type, dir;
	
	Camera(int y, int x, int type) {
		this.y = y;
		this.x = x;
		this.type = type;
	}
}
