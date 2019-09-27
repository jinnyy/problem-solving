/*
 * [백준][14891] 톱니바퀴
 * 13180 KB	76 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] wheel = new boolean[4][8];
		for(int i=0; i<4; i++) {
			String str = br.readLine().trim();
			for(int j=0; j<8; j++) {
				wheel[i][j] = str.charAt(j) == '1';	// S=true, N=false
			}
		}
		int[] top = {0,0,0,0};
		int K = Integer.parseInt(br.readLine());
		for(int k=0; k<K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) * -1;	// -1=시계, 1=반시계
			
			int ndir = dir;
			int[] ntop = top.clone();
			ntop[n] = (top[n] + ndir + 8) % 8;
			for(int left=n-1; left>=0; left--) {
				boolean l = wheel[left+1][(top[left+1]+6)%8];
				boolean r = wheel[left][(top[left]+2)%8];
				if(l != r) ndir = -ndir;
				else break;
				
				// 돌리기
				ntop[left] = (top[left] + ndir + 8) % 8;
			}
			ndir = dir;
			for(int right=n+1; right<4; right++) {
				boolean r = wheel[right-1][(top[right-1]+2)%8];
				boolean l = wheel[right][(top[right]+6)%8];
				if(r != l) ndir = -ndir;
				else break;
				
				ntop[right] = (top[right] + ndir + 8) % 8;
			}
			top = ntop;
		}
		br.close();
		
		int score = 0;
		int mult = 1;
		for(int i=0; i<4; i++) {
			if(wheel[i][top[i]]) {
				score += mult;
			}
			mult *= 2;
		}
		System.out.print(score);
	}

}
