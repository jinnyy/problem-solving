/*
 * [SWEA][1767] 프로세서 연결하기
 * 77,636 kb	310 ms
 */
package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class ConnectProcessor {
	static int N, Min[];
	static boolean connected[][], visited[];
	static ArrayList<Core> cores;
	static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			cores = new ArrayList<Core>();
			connected = new boolean[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					connected[i][j] = Integer.parseInt(st.nextToken())==1 ? true : false;
					if(connected[i][j]) {
						cores.add(new Core(i, j));
					}
				}
			}
			Min = new int[N+1];
			for(int i=0; i<=N; i++) {
				Min[i] = 144;
			}
			
			visited = new boolean[N];
			connect(0, 0, 0);
			
			int max = 0;
			int[] MAX_cache = Min;
			for(int i=N; i>=0; i--) {
				if(Min[i]!=144) {
					max = Min[i];
					break;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(max);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	private static void connect(int depth, int cnt, int connectedCnt) {
		Min[connectedCnt] = Math.min(Min[connectedCnt], cnt);
		if(depth>=cores.size()) {
			return;
		}
		visited[depth] = true;
		Core cur = cores.get(depth);
		// 가장자리면
		if(cur.y==0 || cur.x==0 || cur.y==N-1 || cur.x==N-1) {
			connect(depth+1, cnt, connectedCnt+1);
		}
		// 가장자리가 아니면 가능한 방향들 연결해봄
		else {
			// 1) connected 캐싱
			int rowIdx = cur.y;
			int colIdx = cur.x;
			boolean[] row = new boolean[N];
			boolean[] col = new boolean[N];
			for(int i=0; i<N; i++) {
				row[i] = connected[rowIdx][i];
				col[i] = connected[i][colIdx];
			}
			// 2) 연결 가능한 방향 구하기
			boolean[] dirs = getVisitables(cur.y, cur.x);
			// 3-1) 연결하지 않는 경우
			connect(depth+1, cnt, connectedCnt);
			// 3-2) 연결하는 경우
			for(int dir=0; dir<4; dir++) {
				if(dirs[dir]) {
					// connect 표시하고
					int idx = 1;
					while(true) {
						int y = cur.y + idx * dy[dir];
						int x = cur.x + idx * dx[dir];
						connected[y][x] = true;
						if(y<=0 || x<=0 || y>=N-1 || x>=N-1) break;
						idx++;
					}
					
					// connect(depth+1)
					connect(depth+1, cnt+idx, connectedCnt+1);
					
					// connected 초기화
					for(int i=0; i<N; i++) {
						connected[rowIdx][i] = row[i];
						connected[i][colIdx] = col[i];
					}
				}
			}
		}
		visited[depth] = false;
	}
	
	private static boolean[] getVisitables(int cy, int cx) {
		boolean[] dirs = new boolean[4];
		for(int i=0; i<4; i++) {
			int y=cy, x=cx;
			boolean visitable = true;
			while(true) {
				y += dy[i];
				x += dx[i];
				if(y<0 || y>=N || x<0 || x>=N) break;
				if(connected[y][x]) {
					visitable = false;
					break;
				}
			}
			dirs[i] = visitable;
		}
		return dirs;
	}
	
}


class Core {
	int y, x;
	
	Core(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
  // for debugging
	@Override
	public String toString() {
		return String.format("(%d, %d)", this.y, this.x);
	}
}
