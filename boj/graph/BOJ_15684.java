/*
 * [백준][15684] 사다리 조작
 * 19700 KB	608 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, H, min;
	static boolean map[][], found;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H+1][N+2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
		}
		br.close();
		
		min = 301;
		found = false;
		for(int ladder=0; ladder<=3; ladder++) {
			add(1, 1, 0, ladder);
			if(found) break;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(min>3 ? "-1" : String.valueOf(min));
		bw.close();
	}
	
	static void add(int cy, int cx, int cnt, int goal) {
		if(found) return;
		if(cnt == goal) {
			boolean[][] map_cache = map;
			for(int x=0; x<N; x++) {
				if(go(x) != x) return;
			}
			if(cnt < min) {
				min = cnt;
				found = true;
			}
		}
		
		for(int x=cx; x<=N; x++) {
			if(ableToPut(cy, x)) {
				map[cy][x] = true;
				add(cy, x, cnt+1, goal);
				map[cy][x] = false;
			}
		}
		for(int y=cy+1; y<=H; y++) {
			for(int x=1; x<=N; x++) {
				if(ableToPut(y, x)) {
					map[y][x] = true;
					add(y, x, cnt+1, goal);
					map[y][x] = false;
				}
			}
		}
	}
	
	private static boolean ableToPut(int y, int x) {
		if(y<1 || x<1 || y>H || x>N) return false;
		if(map[y][x] || map[y][x-1] || map[y][x+1]) return false;
		return true;
	}
	
	static int go(int start) {
		int y = 0;
		int x = start;
		
		while(y<=H) {
			if(map[y][x]) x--;
			else if(map[y][x+1]) x++;
			y++;
		}
		return x;
	}
}
