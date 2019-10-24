/*
 * [백준][17136] 색종이 붙이기
 * 21320 KB	196 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static final int MAX = 26;
	static int min, cnt, paper[] = {0, 5, 5, 5, 5, 5};
	static boolean map[][];
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new boolean[10][10];
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<10; j++) {
				map[i][j] = st.nextToken().equals("1");
			}
		}
		br.close();
		
		min = MAX;
		cnt = 0;
		tryAll(0, 0);
		System.out.print(min==MAX ? -1 : min);
	}
	
	static void tryAll(int y, int x) {
		if(y == 10) { min = Math.min(min, cnt); return; }
		if(x == 10) { tryAll(y+1, 0); return; }
		if(!map[y][x]) {
			tryAll(y, x+1);
		} else {
			for(int len=5; len>0; len--) {
				if(paper[len]==0 || y+len>10 || x+len>10) continue;
				if(passed(len, y, x)) {
					fill(len, y, x);
					tryAll(y, x+len);
					reset(len, y, x);
				}
			}
		}
	}
	
	
	static boolean passed(int length, int sy, int sx) {
		for(int y=0; y<length; y++) {
			for(int x=0; x<length; x++) {
				if(!map[sy+y][sx+x]) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void fill(int length, int sy, int sx) {
		for(int y=0; y<length; y++) {
			for(int x=0; x<length; x++) {
				map[sy+y][sx+x] = false;
			}
		}
		paper[length]--;
		cnt++;
	}
	
	static void reset(int length, int sy, int sx) {
		for(int y=0; y<length; y++) {
			for(int x=0; x<length; x++) {
				map[sy+y][sx+x] = true;
			}
		}
		paper[length]++;
		cnt--;
	}
	
	static boolean check(boolean[][] visited) {
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(!visited[i][j] && map[i][j]) return false;
			}
		}
		return true;
	}

}
