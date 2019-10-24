/*
 * [백준][17822] 원판 돌리기
 * 14580 KB	124 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static final int[] dy = {1, 0};
	static final int[] dx = {0, 1};
	static int N, M, T, disc[][], start[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		disc = new int[N+1][M];
		start = new int[N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				disc[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());	// x의 배수
			int d = Integer.parseInt(st.nextToken());	// 0=시계 1=반시계
			int k = Integer.parseInt(st.nextToken());	// k칸 회전
			// 돌리기
			for(int id=x; id<=N; id+=x)
				turn(id, d, k);
			// 인접 & 수 같은거 제거
			if(!remove())
				modify();
		}
		br.close();
		
		System.out.print(sum());
	}
	
	static int sum() {
		int sum = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				sum += disc[i][j];
			}
		}
		return sum;
	}
	
	// 누구를 얼만큼 돌리는지
	static void turn(int id, int d, int k) {
		k %= M;
		if(d==0) k = M-k;
		start[id] = (start[id] + k) % M;
	}
	
	// 인접한 값 제거 (모든 원판)
	// 연속해서 같은 값 고려
	static boolean remove() {
		boolean[][] removed = new boolean[N+1][M];
		// i 번째 원판
		for(int i=1; i<=N; i++) {
			// j 번째 숫자
			for(int j=0; j<M; j++) {
				int x = (start[i] + j) % M;
				if(disc[i][x] == 0) continue;
				int cur = disc[i][x];
				for(int d=0; d<2; d++) {
					int ny = i + dy[d];
					if(ny<=0 || ny>N) continue;
					int nx = (start[ny] + j + dx[d] + M) % M;
					if(disc[ny][nx] == cur) {
						removed[ny][nx] = true;
						removed[i][x] = true;
					}
				}
			}
		}
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(removed[i][j]) {
					disc[i][j] = 0;
					cnt++;
				}
			}
		}
		return cnt>0;
	}
	
	
	static void modify() {
		float sum = 0;
		float cnt = 0;
		for(int id=1; id<=N; id++) {
			for(int j=0; j<M; j++) {
				if(disc[id][j] == 0) continue;
				sum += disc[id][j];
				cnt++;
			}
		}
		float avg = sum / cnt;
		for(int id=1; id<=N; id++) {
			for(int j=0; j<M; j++) {
				if(avg == disc[id][j] || disc[id][j] == 0) continue;
				else if(avg < disc[id][j]) disc[id][j]--;
				else disc[id][j]++;
			}
		}
	}
}
