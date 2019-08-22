/*
 * [SWEA][5650] 핀볼 게임
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRF8s6ezEDFAUo&
 */
package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class PinBall {
	static final int[] dy = {0, -1, 0, 1}; // 왼 위 오 아래
	static final int[] dx = {-1, 0, 1, 0};
	static int N, map[][], Max;
	static Point wormhole[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			wormhole = new Point[6][2];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 5) {
						if(wormhole[map[i][j]-5][0]==null) {
							wormhole[map[i][j]-5][0] = new Point(i, j);
						} else {
							wormhole[map[i][j]-5][1] = new Point(i, j);
						}
					}
				}
			}
			Max = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==0) {
						for(int dir=0; dir<4; dir++) {
							go(i, j, dir);
						}
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(Max);
			bw.append(sb.toString());
			bw.newLine();
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	private static void go(int sy, int sx, int dir) {
		int cy = sy;
		int cx = sx;
		boolean start = true;
		int score = 0;
		while(true) {
			int block = (cy<0 || cx<0 || cy>=N || cx>=N) ? 5 :map[cy][cx];
			if(block==-1 || (sy==cy && sx==cx && !start)) {
				Max = Math.max(Max, score);
				return;
			}
			// 웜홀이면 다른 웜홀로 이동
			if(block > 5 && !start) {
				Point next = wormhole[map[cy][cx]-5][0];
				if(next.y==cy && next.x==cx) next = wormhole[map[cy][cx]-5][1];
				cy = next.y;
				cx = next.x;
				start = true;
			}
			// 블록이 없으면 -> 그대로
			else if(block==0 || block>5) {
				cy += dy[dir];
				cx += dx[dir];
				start = false;
			}
			// 블록이 있으면
			else {
				switch(block) {
					case 1:
						if(dir==0) dir++; // 왼 -> 위
						else if(dir==3) dir--; // 아래 -> 오
						else dir = (dir+2)%4;
						break;
					case 2:
						if(dir==0) dir = 3; // 왼 -> 아래
						else if(dir==1) dir++; // 위 -> 오
						else dir = (dir+2)%4;
						break;
					case 3:
						if(dir==2) dir++; // 오 -> 아래
						else if(dir==1) dir--; // 위 -> 왼
						else dir = (dir+2)%4;
						break;
					case 4:
						if(dir==2) dir--; // 오 -> 위
						else if(dir==3) dir = 0; // 아래 -> 왼
						else dir = (dir+2)%4;
						break;
					case 5:
						dir = (dir+2)%4;
						if(start) score--;
						break;
				}
				cy += dy[dir];
				cx += dx[dir];
				score++;
				start = false;
			}
		}
	}
}


class Point {
	int y, x;
	
	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
