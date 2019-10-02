/*
 * [백준][16234] 인구 이동
 * 278492 KB	524 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static int N, L, R, map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		int cnt = 0;
		while(true) {			
			boolean finish = true;
			boolean[][] visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					if(!bfs(new Nation(i, j), visited)) {
						finish = false;
					}
				}
			}
			if(finish) break;
			cnt++;
		}
		System.out.print(cnt);
		
	}
	
	static boolean bfs(Nation start, boolean[][] visited) {
		int sum = 0;
		LinkedList<Nation> path = new LinkedList<Nation>();
		LinkedList<Nation> Q = new LinkedList<Nation>();
		Q.add(start);
		visited[start.y][start.x] = true;
		
		while(!Q.isEmpty()) {
			Nation cur = Q.poll();
			path.add(cur);
			sum += map[cur.y][cur.x];
			
			for(int dir=0; dir<4; dir++) {
				int ny = cur.y + dy[dir];
				int nx = cur.x + dx[dir];
				if(ny<0 || nx<0 || ny>=N || nx>=N || visited[ny][nx]) continue;
				if(connected(cur.y, cur.x, ny, nx)) {
					visited[ny][nx] = true;
					Q.add(new Nation(ny, nx));
				}
			}
		}
		
		int average = sum / path.size();
		for(Nation p : path)
			map[p.y][p.x] = average;
		return path.size() == 1;
	}
	
	static boolean connected(int y1, int x1, int y2, int x2) {
		int diff = Math.abs(map[y1][x1] - map[y2][x2]);
		return L <= diff && diff <= R;
	}
}

class Nation {
	int y, x;
	
	Nation(int y, int x){
		this.y = y;
		this.x = x;
	}
}
