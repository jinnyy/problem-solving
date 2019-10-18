/*
 * [백준][15684] 사다리 조작
 * 16068 KB	408 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N, M, H, goal;
	static boolean map[][], found;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
		}
		br.close();
		
		
		found = false;
		int cnt;
		for(cnt=0; cnt<=3; cnt++) {
			goal = cnt;
			select(0, 1, 0);
			if(found) break;
		}
		System.out.print(found ? cnt : -1);
	}
	
	
	static void select(int depth, int cy, int cx) {
		if(depth == goal) {
			if(check()) found = true;
			return;
		}
		for(int ny=cy; ny<=H; ny++) {
			int nx = 1;
			if(ny == cy) nx = cx+1;
			for(; nx<N; nx++) {
				if(visitable(ny, nx)) {
					map[ny][nx] = true;
					select(depth+1, ny, nx);
					if(found) return;
					map[ny][nx] = false;
				}
			}
		}
	}
	
	
	private static boolean check() {
		for(int x=1; x<=N; x++) {
			if(go(x) != x) return false;
		}
		return true;
	}
	
	private static int go(int start) {
		int y = 0;
		int x = start;
		while(y<=H) {
			if(map[y][x-1]) x--;
			else if(map[y][x]) x++;
			y++;
		}
		return x;
	}
	
	private static boolean visitable(int y, int x) {
		if(y<0 || x<0 || y>H || x>=N) return false;
		if(map[y][x] || (x>1 && map[y][x-1]) || (x<N-2 && map[y][x+1])) return false;
		return true;
	}
}
