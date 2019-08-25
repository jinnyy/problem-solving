/*
 * [SWEA][5656] 벽돌 깨기
 * 97,276 KB	300 ms
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
 */
package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class BreakBricks {
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static int N, W, H, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// N=구슬개수
			W = Integer.parseInt(st.nextToken());	// W=너비
			H = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[H+1][W];
			int[] top = new int[W];
			for(int i=0; i<W; i++) {
				top[i] = H;
			}
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<W; j++) {
					int brick = Integer.parseInt(st.nextToken());
					map[i][j] = brick;
					if(map[i][j]!=0 && top[j]==H) top[j]=i; 
				}
			}
			
			// drop;
			min = 220;
			drop(0, map, top);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(min);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	// 구슬 떨어뜨리기
	private static void drop(int depth, int[][] map, int[] top) {
		if(N==depth) {
			int cnt = 0;
			for(int t : top) {
				cnt += (H - t);
			}
			min = Math.min(min, cnt);
			return;
		}
		
		// 끝이 아니면 맨 왼쪽부터 맨 오른쪽까지 구슬을 떨어뜨려 본다. -> 다음 depth로
		for(int i=0; i<W; i++) {
			// 복구할 위치를 기억한다.
			int[][] prevMap = new int[H+1][W];
			int[] prevTop = new int[W];
			for(int w=0; w<W; w++) {
				for(int h=top[w]; h<H; h++) {
					prevMap[h][w] = map[h][w];
				}
			}
			for(int w=0; w<W; w++) {
				prevTop[w] = top[w];
			}
			// 제거
			if(top[i]<H) {
				breakBricks(top[i], i, map);
				pushDown(map, top);		// 다 제거한 다음에 블록들을 밑으로 내린다.
			}
			drop(depth+1, map, top);	// 다음 depth
			
			map = prevMap;
			top = prevTop;
		}
	}
	
	private static void breakBricks(int cy, int cx, int[][] map) {
		LinkedList<Brick> Q = new LinkedList<Brick>();
		Q.add(new Brick(cy, cx));
		while(!Q.isEmpty()) {
			Brick cur = Q.poll();
			int L = map[cur.y][cur.x];
			for(int length=1; length<L; length++) {
				if(length>W) break;
				for(int j=0; j<4; j++) {
					int ny = cur.y + dy[j] * length;
					int nx = cur.x + dx[j] * length;
					if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
					Q.add(new Brick(ny, nx));
				}
			}
			map[cur.y][cur.x] = 0;
		}
		
	}
	
	private static void pushDown(int[][] map, int[] top) {
		for(int w=W-1; w>=0; w--) {
			int cnt = 0;
			for(int h=H-1; h>=0; h--) {
				if(map[h][w]==0) {
					cnt++;
				} else {
					if(cnt==0) continue;
					map[h+cnt][w] = map[h][w];
					map[h][w] = 0;
				}
			}
			// top 계산
			for(int h=top[w]; h<H; h++) {
				if(map[h][w]!=0) {
					top[w]=h;
					break;
				}
			}
			if(map[top[w]][w]==0) top[w] = H;
		}
	}
}


class Brick {
	int y, x;
	
	Brick(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", this.y, this.x);
	}
}

