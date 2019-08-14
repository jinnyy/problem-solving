/*
 * [백준][2580] 스도쿠
 * 278396KB 588ms
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayList;


public class Sudoku {
	static int map[][];
	static ArrayList<Point> points;
	static boolean found;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		points = new ArrayList<Point>();
		for(int i=0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) points.add(new Point(i, j));
			}
		}
		
		// 연산
		found = false;
		find(0);
		
		// 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<9; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<9; j++) {
				sb.append(map[i][j]);
				sb.append(" ");
			}
			bw.append(sb.toString());
			bw.newLine();
		}
		bw.flush();
	}
	
	static void find(int depth) throws Exception {
		if(depth==points.size()) {
			found = true;
			return;
		}
		Point cur = points.get(depth);
		cur.check(map);
		for(int cand : cur.visitable) {
			map[cur.y][cur.x] = cand;
			find(depth+1);
			if(found) return;
			map[cur.y][cur.x] = 0;
		}
	}
}

class Point {
	int y, x, val;
	LinkedList<Integer> visitable;
	
	Point(int y, int x){
		this.y = y;
		this.x = x;
		this.val = 0;
	}
	
	void check(int[][] map) {
		LinkedList<Integer> visitable = new LinkedList<Integer>();
		boolean[] locV = new boolean[10];
		boolean[][] visiteds = new boolean[][] {checkRow(map), checkCol(map), checkBox(map)};
		for(boolean[] visited : visiteds) {
			for(int i=0; i<10; i++) {
				if(visited[i]) locV[i] = true;
			}
		}
		for(int i=1; i<=9; i++)
			if(!locV[i]) visitable.add(i);
		this.visitable = visitable;
	}
	
	boolean[] checkRow(int[][] map) {
		boolean[] ret = new boolean[10];
		for(int i=0; i<9; i++) {
			ret[map[this.y][i]] = true;
		}
		return ret;
	}
	boolean[] checkCol(int[][] map) {
		boolean[] ret = new boolean[10];
		for(int i=0; i<9; i++) {
			ret[map[i][this.x]] = true;
		}
		return ret;
	}
	boolean[] checkBox(int[][] map) {
		boolean[] ret = new boolean[10];
		int sy = (this.y/3) * 3;
		int sx = (this.x/3) * 3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				ret[map[sy+i][sx+j]] = true;
			}
		}
		return ret;
	}
}
