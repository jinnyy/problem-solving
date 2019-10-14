/*
 * [백준][1992] 쿼드 트리
 * 13516 KB	80 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static String image[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		image = new String[N];
		for(int i=0; i<N; i++) {
			image[i] = br.readLine().trim();
		}
		br.close();
		
		compress(0, 0, N);
		
		System.out.print(sb.toString());
	}
	
	static void compress(int sy, int sx, int length) {
		char dot = image[sy].charAt(sx);
		boolean divide = false;
		outer:
		for(int y=sy; y<sy+length; y++) {
			for(int x=sx; x<sx+length; x++) {
				if(dot != image[y].charAt(x)) {
					divide = true;
					break outer;
				}
			}
		}
		if(divide) {
			sb.append("(");
			length /= 2;
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					compress(sy + i*length, sx + j*length, length);
				}
			}
			sb.append(")");
		} else {
			sb.append(dot);
		}
	}
}
