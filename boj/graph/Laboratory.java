/*
 * [백준][14502] 연구소
 * 145856 KB	376 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static final int[] dy = {1, -1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static final int BLANK=0, WALL=1, VIRUS=2;
	static int N, M, max;
	static LinkedList<Point> viruses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		viruses = new LinkedList<Point>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == VIRUS) viruses.add(new Point(i, j));
			}
		}
		br.close();
		
		// calculate
		max = 0;
		choose(map, 0, 0, 0);
		
		// write
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(max));
		bw.close();
	}
	
	private static void choose(int[][] map, int cy, int cx, int depth) {
		if(depth==3) {
			max = Math.max(max , simulate(map));
			return;
		}
		// y==cy
		for(int j=cx; j<M; j++) {
			if(map[cy][j] != BLANK) continue;
			map[cy][j] = WALL;
			choose(map, cy, j, depth+1);
			map[cy][j] = BLANK;
		}
		// y>cy
		for(int i=cy+1; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != BLANK) continue;
				map[i][j] = WALL;
				choose(map, i, j, depth+1);
				map[i][j] = BLANK;
			}
		}
		
	}
	
	private static int simulate(int[][] orgMap) {
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++)
			map[i] = orgMap[i].clone();
		
		LinkedList<Point> Q = new LinkedList<Point>();
		for(Point v : viruses) {
			Q.add(v);	// v 부분은 map에서 이미 2(VIRUS)
		}
		
		while(!Q.isEmpty()) {
			Point cur = Q.poll();
			for(int i=0; i<4; i++) {
				int y = cur.y + dy[i];
				int x = cur.x + dx[i];
				if(y<0 || x<0 || y>=N || x>=M || map[y][x] != BLANK) continue;
				map[y][x] = VIRUS;
				Q.add(new Point(y, x));
			}
		}
		
		int size = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==BLANK)
					size++;
			}
		}
		return size;
	}
}

class Point {
	int y, x;
	Point(int y, int x){
		this.y = y;
		this.x = x;
	}
}
