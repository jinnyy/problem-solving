/*
 * [백준][147890] 경사로
 * 14116 KB	92 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		// 검사
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(checkRow(map, i)) cnt++;
			if(checkCol(map, i)) cnt++;
		}
		System.out.println(cnt);
	}
	
	private static boolean checkRow(int[][] map, int row) {
		int length = 1;
		int dir = 0;
		for(int x=1; x<N; x++) {
			int diff = map[row][x] - map[row][x-1];
			if(diff == 0) {
				length++;
			} else if(diff == 1) {
				if(dir == -1 && length < 2*L) return false;
				else if(length < L) return false;
				length = 1;
				dir = diff;
			} else if(diff == -1) {
				if(dir == -1 && length < L)
					return false;
				length = 1;
				dir = diff;
			} else
				return false;
		}
		if(dir == -1 && length < L) return false;
		return true;
	}
	
	private static boolean checkCol(int[][] map, int col) {
		int length = 1;
		int dir = 0;
		for(int y=1; y<N; y++) {
			int diff = map[y][col] - map[y-1][col];
			if(diff == 0) {
				length++;
			} else if(diff == 1) {
				if(dir == -1 && length < 2*L) return false;
				else if(length < L) return false;
				length = 1;
				dir = diff;
			} else if(diff == -1) {
				if(dir == -1 && length < L)
					return false;
				length = 1;
				dir = diff;
			} else
				return false;
		}
		if(dir == -1 && length < L) return false;
		return true;
	}
}
