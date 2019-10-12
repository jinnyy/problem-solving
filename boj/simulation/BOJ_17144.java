/*
 * [백준][17144] 미세먼지 안녕!
 * 24760 KB	228 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static int R, C;
	
	public static void main(String[] args) throws Exception {
		int T, A[][], x = 0, y[] = {-1, -1};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		A = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				int status = Integer.parseInt(st.nextToken());
				if(status == -1) {
					if(y[0] == -1) y[0] = i;
					else y[1] = i;
					x = j;
				}
				A[i][j] = status;
			}
		}
		br.close();
		
		while(T-- > 0) {
			A = diffuse(A);
			clean(x, y, A);
		}
		
		OutputStreamWriter ow = new OutputStreamWriter(System.out);
		ow.append(String.valueOf(count(A)));
		ow.close();
	}
	
	private static int count(int[][] map) {
		int cnt = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == -1) continue;
				cnt += map[i][j];
			}
		}
		return cnt;
	}
	
	static void clean(int x, int[] y, int[][] map) {
		for(int yIdx=0; yIdx<2; yIdx++) {
			int dx = 1;
			int dy = 0;
			int prev = 0;
			int cy = y[yIdx] + dy;
			int cx = x + dx;
			while(!(cy==y[yIdx] && cx==x)) {
				int ny, nx;
				int cur = map[cy][cx];
				map[cy][cx] = prev;
				prev = cur;
				ny = cy + dy;
				nx = cx + dx;
				if((cy!=0 && ny==0) || (cy!=R-1 && ny==R-1)) { dy = 0; dx = -1; }
				else if(cx!=0 && nx==0) {
					if(yIdx==0) {dy = 1; dx = 0;}
					else {dy = -1; dx = 0;}
				} else if(cx!=C-1 && nx==C-1) {
					if(yIdx==0) {dy = -1; dx = 0;}
					else {dy = 1; dx = 0;}
				}
				cy = ny;
				cx = nx;
			}
		}
		
	}
	
	static int[][] diffuse(int[][] A) {
		int[][] map = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				int cnt = 0;
				for(int dir=0; dir<4; dir++) {
					int ny = i + dy[dir];
					int nx = j + dx[dir];
					if(ny<0 || nx<0 || ny>=R || nx>=C || A[ny][nx]==-1) continue;
					map[ny][nx] += A[i][j] / 5;
					cnt++;
				}
				A[i][j] -= (A[i][j] / 5) * cnt;
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] += A[i][j];
			}
		}
		
		return map;
	}
}
