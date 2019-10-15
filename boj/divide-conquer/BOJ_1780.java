/*
 * [백준][1780] 종이의 개수
 * 300064 KB	744 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N, paper[][], cnt[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		
		cnt = new int[3];
		divide(0, 0, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<3; i++) {
			sb.append(cnt[i]);
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void divide(int cy, int cx, int length) {		
		if(equal(cy, cx, length)) return;
		length /= 3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				divide(cy + length*i, cx + length*j, length);
			}
		}
	}

	static boolean equal(int cy, int cx, int length) {
		int num = paper[cy][cx];
		for(int y=cy; y<cy+length; y++) {
			for(int x=cx; x<cx+length; x++) {
				if(num != paper[y][x])
					return false;
			}
		}
		cnt[num+1]++;
		return true;
	}
}
