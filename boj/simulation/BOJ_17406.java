/*
 * [백준][17406] 배열 돌리기 4
 * 32576 KB	180 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static final int[] dy = {0, 1, 0, -1};	// 우 하 좌 상
	static final int[] dx = {1, 0, -1, 0};
	static final int MAX = 5001;
	static int N, M, K, A[][], ops[][], min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ops = new int[K][3];
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			ops[k][0] = Integer.parseInt(st.nextToken()) - 1;
			ops[k][1] = Integer.parseInt(st.nextToken()) - 1;
			ops[k][2] = Integer.parseInt(st.nextToken());
		}
		br.close();
		min = MAX;
		select(0, new int[K], new boolean[K]);
		System.out.print(min);
	}
	
	// i번째에 몇 번 인덱스 연산을 수행
	static void select(int depth, int[] seq, boolean[] visited) {
		if(depth == K) {
			int[][] locA = new int[N][M];
			for(int i=0; i<N; i++)
				locA[i] = A[i].clone();
			for(int k=0; k<K; k++) {
				int[] op = ops[seq[k]];
				for(int s=1; s<=op[2]; s++) {
					turn(op[0]-s, op[1]-s, s, locA);
				}
			}
			min = Math.min(min, getVal(locA));
		}
		
		for(int i=0; i<K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			seq[depth] = i;
			select(depth+1, seq, visited);
			visited[i] = false;
		}
	}
	
	static void turn(int fy, int fx, int s, int[][] A) {
		int ty = fy + 2*s;
		int tx = fx + 2*s;
		int d = 0;
		int cy = fy + dy[d];
		int cx = fx + dx[d];
		int prev = A[fy][fx];
		while(true) {
			// 값 넣기
			int cur = A[cy][cx];
			A[cy][cx] = prev;
			prev = cur;
			// 다음 위치
			int ny = cy + dy[d];
			int nx = cx + dx[d];
			if(cy==fy && cx==fx) break;
			// 끝이면 방향 바꾸기
			if(cx!=tx && nx==tx) d = 1;
			else if(cy!=ty && ny==ty) d = 2;
			else if(cx!=fx && nx==fx) d = 3;
			cy = ny;
			cx = nx;
		}
	}
	
	static int getVal(int[][] A) {
		int min = 50 * 100;
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=0; j<M; j++) {
				sum += A[i][j];
			}
			if(sum < min) min = sum;
		}
		return min;
	}
}
