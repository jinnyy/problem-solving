/*
 * [백준][14503] 로봇 청소기
 * 13408 KB	80 ms
 */
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static final int[] dy = {-1, 0, 1, 0};	// 북동남서 = 상우하좌
	static final int[] dx = {0, 1, 0, -1};
	static final int BLANK=0, WALL=1, CLEANED=2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		int cnt = 1;
		map[r][c] = CLEANED;
		
		// 청소
		int checked = 0;
		while(true) {
			int dir = (d+3) % 4;
			if(map[r+dy[dir]][c+dx[dir]] == BLANK) {
				d = dir;
				r += dy[dir];
				c += dx[dir];
				map[r][c] = CLEANED;
				cnt++;
				checked = 0;
			} else {
				d = dir;
				checked++;
				if(checked == 4) {
					dir = (d+2) % 4;
					r += dy[dir];
					c += dx[dir];
					if(map[r][c] == WALL) {
						break;
					}
					checked = 0;
				}
			}		
		}
		
		// write
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(cnt));
		bw.close();
	}

}
