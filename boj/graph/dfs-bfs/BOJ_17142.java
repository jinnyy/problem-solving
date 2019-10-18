/*
 * [백준][17142] 연구소 3
 * 41980 KB	192 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;


public class Main {
	static final int EMPTY = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	static final int MAX = 2500;
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static int N, M, map[][], V, min, startCnt;
	static ArrayList<int[]> Viruses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		startCnt = 0;
		Viruses = new ArrayList<int[]>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == VIRUS) Viruses.add(new int[] {i, j});
				if(map[i][j] != WALL) startCnt++;
			}
		}
		br.close();
		V = Viruses.size();
		min = MAX;
		select(0, -1, new int[M]);
		
		System.out.print(min==MAX ? -1 : min);
	}
	
	
	static void select(int depth, int cur, int[] selected) {
		if(depth == M) {
			min = Math.min(min, bfs(selected));
			return;
		}
		
		for(int next=cur+1; next<V; next++) {
			selected[depth] = next;
			select(depth+1, next, selected);
			selected[depth] = 0;
		}
	}
	
	static int bfs(int[] selected) {
		int time = 1;
		int cnt = startCnt;
		LinkedList<int[]> Q = new LinkedList<int[]>();
		int[][] timeMap = new int[N][N];
		for(int i=0; i<M; i++) {
			int[] v = Viruses.get(selected[i]);
			Q.add(v);
			timeMap[v[0]][v[1]] = 1;
		}
		
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			cnt--;
			
			for(int i=0; i<4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(ny<0 || nx<0 || ny>=N || nx>=N || map[ny][nx]==WALL || timeMap[ny][nx]>0) continue;
				timeMap[ny][nx] = timeMap[cur[0]][cur[1]] + 1;
				if(map[ny][nx] != VIRUS)
					time = Math.max(time, timeMap[ny][nx]);
				Q.add(new int[] {ny, nx});
			}
		}
		
		return cnt==0 ? time - 1 : MAX;
	}
}
