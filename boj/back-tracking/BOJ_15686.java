/*
 * [백준][15686] 치킨 배달
 * 13740 KB	132 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;



public class Main {
	static final int HOUSE=1, CHICKEN=2;
	static int N, M, map[][], min;
	static ArrayList<Point> houses, chickens;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		chickens = new ArrayList<Point>();
		houses = new ArrayList<Point>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == CHICKEN) chickens.add(new Point(i, j));
				else if(map[i][j] == HOUSE) houses.add(new Point(i, j));
			}
		}
		br.close();
		
		int[] chosen = new int[M];
		for(int i=0; i<M; i++) {
			chosen[i] = -1;
		}
		min = 2500;
		choose(-1, 0, chosen);
		
		System.out.print(min);
	}
	
	static void choose(int cur, int depth, int[] chosen) {
		if(depth == M) {
			min = Math.min(min, getDistance(chosen));
			return;
		}
		
		for(int next=cur+1; next<chickens.size(); next++) {
			chosen[depth] = next;
			choose(next, depth+1, chosen);
			chosen[depth] = -1;
		}
	}
	
	static int getDistance(int[] chosen) {
		int distance = 0;
		for(int h=0; h<houses.size(); h++) {
			int loc = 101;
			for(int c=0; c<chosen.length; c++) {
				if(c == -1) break;
				int cur = chosen[c];
				int dist = houses.get(h).getDist(chickens.get(cur));
				loc = Math.min(loc, dist);
			}
			distance += loc;
		}
		
		return distance;
	}
}

class Point {
	int y, x;
	
	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	int getDist(Point p) {
		return Math.abs(this.y - p.y) + Math.abs(this.x - p.x);
	}
}
