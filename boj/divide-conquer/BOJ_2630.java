/*
 * [백준][2630] 색종이 만들기
 * 14648 KB	112 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static final int WHITE = 0, BLUE = 1;
	static int N, paper[][], count[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		count = new int[2];
		check(0, 0, N);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(String.valueOf(count[WHITE]));
		bw.newLine();
		bw.append(String.valueOf(count[BLUE]));
		bw.close();
	}
	
	static void check(int sy, int sx, int length) {
		int color = paper[sy][sx];
		boolean divided = false;
		outer:
		for(int y=sy; y<sy+length; y++) {
			for(int x=sx; x<sx+length; x++) {
				if(color != paper[y][x]) {
					divided = true;
					break outer;
				}
			}
		}
		if(divided) {
			length /= 2;
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					int ny = sy + i*length;
					int nx = sx + j*length;
					check(ny, nx, length);
				}
			}
			
		} else {
			count[color]++;
		}
	}

}
