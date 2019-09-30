/*
 * [백준][15685] 드래곤 커브
 * 13988 KB	92 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;


public class Main {
	static final int[] dy = {0, -1, 0, 1};
	static final int[] dx = {1, 0, -1, 0};
	static boolean map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		for(int i=0; i<N; i++) {
			LinkedList<Point >curve = new LinkedList<Point>();
			curve.add(new Point(br.readLine()));
			map[curve.peek().y][curve.peek().x] = true;
			makeCurve(curve, 0, curve.peekFirst().goal);
			Point tail = curve.peekLast();
			curve.add(new Point(tail.y+dy[tail.dir], tail.x+dx[tail.dir], (tail.dir+1)%4));
			tail = curve.peekLast();
			map[tail.y][tail.x] = true;
			int temp = 1;
		}		
		br.close();
		System.out.print(count());
	}
	
	
	static void makeCurve(LinkedList<Point> curve, int generation, int goal) {
		if(generation == goal) return;
		
		LinkedList<Point> tails = new LinkedList<Point>();
		Iterator<Point> it = curve.descendingIterator();
		Point prev = it.next();
		int py = prev.y;
		int px = prev.x;
		int pd = prev.dir;
		while(it.hasNext()) {
			int cd = (prev.dir + 1) % 4;
			int cy = py + dy[pd];
			int cx = px + dx[pd];
			tails.add(new Point(cy, cx, cd));
			map[cy][cx] = true;
			py = cy;
			px = cx;
			pd = cd;
			prev = it.next();
		}
		tails.add(new Point(py+dy[pd], px+dx[pd], (prev.dir+1)%4));
		map[py+dy[pd]][px+dx[pd]] = true;
		curve.addAll(tails);
		
		makeCurve(curve, generation+1, goal);
	}
	
	private static int count() {
		int cnt = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				boolean exist = true;
				loop:
				for(int y=0; y<2; y++) {
					for(int x=0; x<2; x++) {
						if(!map[i+y][j+x]) {
							exist = false;
							break loop;
						}
					}
				}
				if(exist) cnt++;
			}
		}
		return cnt;
	}

}


class Point {
	int y, x, dir, goal;
	
	Point(int y, int x, int d) {
		this.y = y;
		this.x = x;
		this.dir = d;
	}
	Point(String str){
		StringTokenizer st = new StringTokenizer(str, " ");
		this.x = Integer.parseInt(st.nextToken());
		this.y = Integer.parseInt(st.nextToken());
		this.dir = Integer.parseInt(st.nextToken());
		this.goal = Integer.parseInt(st.nextToken());
	}
}
