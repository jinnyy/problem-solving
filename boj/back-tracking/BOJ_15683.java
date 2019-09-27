/*
 * [백준][15683] 감시
 * 70840 KB	360 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class Main {
	static final int WALL=-1, WATCHING=-2;
	static final int[] dy = {0, -1, 0, 1};	// 우 상 좌 하
	static final int[] dx = {1, 0, -1, 0};
	static int N, M, map[][], min;
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
				int val = 0;
				if(cur == 6) val = -1;
				else if(cur == 1) val = 1;
				else if(cur == 2) val = 5;
				else if(cur == 3) val = 3;
				else if(cur == 4) val = 7;
				else if(cur == 5) val = 15;
				map[i][j] = val;
				if(map[i][j] > 0)
					cameras.add(new Camera(i, j));
			}
		}
		br.close();
		min = 64;
		go(0);
		System.out.print(min);
	}
	
	private static void watch() {
		for(int i=0; i<cameras.size(); i++) {
			Camera cam = cameras.get(i);
			int camdir = map[cam.y][cam.x];
			for(int dir=0; dir<4; dir++) {
				int localDir = camdir >> dir;
				if(localDir % 2 == 1) {
					// 표시
					int y = cam.y + dy[dir];
					int x = cam.x + dx[dir];
					while(y>=0 && x>=0 && y<N && x<M) {
						if(map[y][x] == WALL) break;
						if(map[y][x]==0) map[y][x] = WATCHING;
						y += dy[dir];
						x += dx[dir];
					}
				}
			}
			
		}
	}
	
	private static void go(int depth) {
		if(depth == cameras.size()) {
			int[][] map_cache = new int[N][M];
			for(int i=0; i<N; i++) {
				map_cache[i] = map[i].clone();
			}
			// 카메라 바라보는 방향 표시
			watch();
			
			// 세기
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) cnt++;
				}
			}
			if(cnt < min)
				min = cnt;
			// 초기화
			map = map_cache;
			return;
		}
		
		// backTracking
		Camera cur = cameras.get(depth);
		int prev = map[cur.y][cur.x];
		if(prev == 15) {
			go(depth+1);
		} else if(prev == 5) {
			go(depth+1);
			map[cur.y][cur.x] = 10;
			go(depth+1);
			map[cur.y][cur.x] = prev;
		} else {
			for(int i=0; i<4; i++) {
				int next = (prev << i) % 16;
				next += prev >> (4-i);
				map[cur.y][cur.x] = next;
				go(depth+1);
				map[cur.y][cur.x] = prev;
			}
		}
	}
}

class Camera {
	int y, x;
	
	Camera(int y, int x){
		this.y = y;
		this.x = x;
	}
}
