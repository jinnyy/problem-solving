/*
 * [백준][17142] 연구소 3
 * 41148 KB	220 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class Main {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static final int MAX = 50 * 50;
	static final int WALL = 1;
	static int N, M, map[][], min, goal;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) goal++;
			}
		}
		br.close();
		
		min = MAX;
		select(0, 0, -1, new int[M][2], new boolean[N][N]);
		
		if(min == MAX) System.out.print(-1);
		else System.out.print(min);
	}
	
	static void select(int depth, int cy, int cx, int[][] seq, boolean[][] visited) {
		if(depth == M) {
			min = Math.min(min, bfs(seq));
			return;
		}
		for(int i=cy; i<N; i++) {
			int start = i==cy ? cx+1 : 0;
			for(int j=start; j<N; j++) {
				if(map[i][j]!=2 || visited[i][j]) continue;
				visited[i][j] = true;
				seq[depth] = new int[] {i, j};
				select(depth+1, i, j, seq, visited);
				seq[depth] = new int[2];
				visited[i][j] = false;
			}
		}
	}
	
	static int bfs(int[][] viruses) {
		LinkedList<Virus> Q = new LinkedList<Virus>();
		boolean[][] visited = new boolean[N][N];
		for(int[] virus : viruses) {
			Q.add(new Virus(virus[0], virus[1], 0));
			visited[virus[0]][virus[1]] = true;
		}
		
		int time = 0;
		int cnt = 0;
		while(!Q.isEmpty()) {
			Virus cur = Q.poll();
			time = Math.max(time, cur.time);
			if(map[cur.y][cur.x] == 0) cnt++;
			if(cnt >= goal) break;
			
			for(int d=0; d<4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
				if(!visited[ny][nx] && map[ny][nx]!=WALL) {
					visited[ny][nx] = true;
					Q.add(new Virus(ny, nx, cur.time+1));
				}
			}
		}
		if(cnt >= goal) return time;
		else return MAX;
	}
}

class Virus {
	int y, x, time;
	
	Virus(int y, int x, int time){
		this.y = y;
		this.x = x;
		this.time = time;
	}
}
