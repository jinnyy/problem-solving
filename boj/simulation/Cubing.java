/*
 * [백준][5373] 큐빙
 * 96060 KB	508 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static final int UP=0, LEFT=1, FRONT=2, RIGHT=3, DOWN=4, BACK=5;
	static char[][][] cube;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			cube = initializeCube();
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int rotate=0; rotate<n; rotate++) {
				move(st.nextToken());
			}
			// write
			for(int i=0; i<3; i++) {
				StringBuilder sb = new StringBuilder();
				for(int j=0; j<3; j++) {
					sb.append(cube[UP][i][j]);
				}
				bw.append(sb.toString());
				bw.newLine();
			}
		}
		
		br.close();
		bw.close();
	}
	
	static void move(String how) {
		char[][][] cube_cache = cube;
		
		char front = how.charAt(0);
		int dir = how.charAt(1)=='+' ? 1 : -1;
		int fid = -1;
		
		int[] sides = null;
		int[][][] rows = new int[4][3][2];
		switch(front) {
			case 'U':
				sides = new int[] {BACK, RIGHT, FRONT, LEFT};
				rows[0] = new int[][] {{2,0},{2,1},{2,2}};
				rows[1] = new int[][] {{0,2},{0,1},{0,0}};
				rows[2] = new int[][] {{0,2},{0,1},{0,0}};
				rows[3] = new int[][] {{0,2},{0,1},{0,0}};
				fid = UP;
				break;
			case 'D':
				sides = new int[] {FRONT, RIGHT, BACK, LEFT};
				rows[0] = new int[][] {{2,0},{2,1},{2,2}};
				rows[1] = new int[][] {{2,0},{2,1},{2,2}};
				rows[2] = new int[][] {{0,2},{0,1},{0,0}};
				rows[3] = new int[][] {{2,0},{2,1},{2,2}};
				fid = DOWN;
				break;
			case 'F':
				sides = new int[] {UP, RIGHT, DOWN, LEFT};
				rows[0] = new int[][] {{2,0},{2,1},{2,2}};
				rows[1] = new int[][] {{0,0},{1,0},{2,0}};
				rows[2] = new int[][] {{0,2},{0,1},{0,0}};
				rows[3] = new int[][] {{2,2},{1,2},{0,2}};
				fid = FRONT;
				break;
			case 'B':
				sides = new int[] {DOWN, RIGHT, UP, LEFT};
				rows[0] = new int[][] {{2,0},{2,1},{2,2}};
				rows[1] = new int[][] {{2,2},{1,2},{0,2}};
				rows[2] = new int[][] {{0,2},{0,1},{0,0}};
				rows[3] = new int[][] {{0,0},{1,0},{2,0}};
				fid = BACK;
				break;
			case 'L':
				sides = new int[] {UP, FRONT, DOWN, BACK};
				rows[0] = new int[][] {{0,0},{1,0},{2,0}};
				rows[1] = new int[][] {{0,0},{1,0},{2,0}};
				rows[2] = new int[][] {{0,0},{1,0},{2,0}};
				rows[3] = new int[][] {{0,0},{1,0},{2,0}};
				fid = LEFT;
				break;
			case 'R':
				sides = new int[] {UP, BACK, DOWN, FRONT};
				rows[0] = new int[][] {{2,2},{1,2},{0,2}};
				rows[1] = new int[][] {{2,2},{1,2},{0,2}};
				rows[2] = new int[][] {{2,2},{1,2},{0,2}};
				rows[3] = new int[][] {{2,2},{1,2},{0,2}};
				fid = RIGHT;
				break;
		}
		rotateFront(cube[fid], dir);
		
		int sid = dir==1 ? 0 : 3;
		int side = sides[sid];
		char[] prev = new char[3];	// color
		int[][] pr = rows[sid];
		for(int i=0; i<3; i++)
			prev[i] = cube[side][pr[i][0]][pr[i][1]];
		sid += dir;
		
		while(sid>=0 && sid<=3) {
			side = sides[sid];
			int[][] cr = rows[sid];
			char[] cur = new char[3];
			for(int i=0; i<3; i++)
				cur[i] = cube[side][cr[i][0]][cr[i][1]];
			for(int i=0; i<3; i++) {
				cube[side][cr[i][0]][cr[i][1]] = prev[i];
			}
			
			// copy
			for(int i=0; i<3; i++)
				prev[i] = cur[i];
			pr = cr;
			sid += dir;
		}
		// 마지막
		sid = (sid + 4) % 4;	// 0 or 3
		side = sides[sid];
		int[][] cr = rows[sid];
		for(int i=0; i<3; i++)
			cube[side][cr[i][0]][cr[i][1]] = prev[i];
	}
	
	static void rotateFront(char[][] side, int dir) {
		if(dir > 0) rotateClockwise(side);
		else rotateReverse(side);
	}
	
	private static void rotateClockwise(char[][] side) {
		char[] p = new char[3];
		char[][] nside = new char[3][3];
		for(int i=0; i<3; i++) {
			p[i] = side[0][i];
		}
		char[] c = new char[3];
		for(int i=0; i<3; i++) {
			c[i] = side[i][2];
			nside[i][2] = p[i];
		}
		p = c;
		c = new char[3];
		for(int i=0; i<3; i++) {
			c[i] = side[2][i];
			nside[2][i] = p[2-i];
		}
		p = c;
		c = new char[3];
		for(int i=0; i<3; i++) {
			c[i] = side[i][0];
			nside[i][0] = p[i];
		}
		p = c;
		for(int i=0; i<3; i++) {
			nside[0][i] = p[2-i];
		}
		
		nside[1][1] = side[1][1];
		for(int i=0; i<3; i++) {
			side[i] = nside[i].clone();
		}
	}
	
	private static void rotateReverse(char[][] side) {
		char[][] nside = new char[3][3];
		char[] p = new char[3];
		for(int i=0; i<3; i++) {
			p[i] = side[0][i];
		}
		char[] c = new char[3];
		for(int i=0; i<3; i++) {
			c[i] = side[i][0];
			nside[i][0] = p[2-i];
		}
		p = c;
		c = new char[3];
		for(int i=0; i<3; i++) {
			c[i] = side[2][i];
			nside[2][i] = p[i];
		}
		p = c;
		c = new char[3];
		for(int i=0; i<3; i++) {
			c[i] = side[i][2];
			nside[i][2] = p[2-i];
		}
		p = c;
		for(int i=0; i<3; i++) {
			nside[0][i] = p[i];
		}
		nside[1][1] = side[1][1];
		for(int i=0; i<3; i++) {
			side[i] = nside[i].clone();
		}
	}
	
	static char[][][] initializeCube() {
		char[][][] cube = new char[6][3][3];
		char[] colors = {'w', 'g', 'r', 'b', 'y', 'o'};
		for(int dir=0; dir<6; dir++) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					cube[dir][i][j] = colors[dir];
				}
			}
		}
		return cube;
	}

}
