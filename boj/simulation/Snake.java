/*
 * [백준][3190] 뱀
 * 13916 KB	88 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static final int EMPTY=0, APPLE=1, SNAKE=2, UP=0, DOWN=1, LEFT=2, RIGHT=3;
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		// read
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		int K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = APPLE;
		}
		int L = Integer.parseInt(br.readLine());
		LinkedList<Dir> dirs = new LinkedList<Dir>();
		for(int i=0; i<L; i++) {
			dirs.add(new Dir(br.readLine()));
		}
		br.close();
		
		// calculate
		LinkedList<Block> snake = new LinkedList<Block>();
		snake.add(new Block(1, 1, RIGHT));
		map[1][1] = SNAKE;
		
		int second = 0;
		while(!snake.isEmpty()) {
			Block head = snake.poll();
			if(!dirs.isEmpty()) {
				Dir dir = dirs.poll();
				if(dir.sec == second)
					head.turn(dir.dir);
				else dirs.addFirst(dir);
			}
			
			// 이동
			int ny = head.y + dy[head.dir];
			int nx = head.x + dx[head.dir];
			// 충돌 -> break
			if(ny<1 || nx<1 || ny>N || nx>N) break;
			
			snake.addFirst(head);
			snake.addFirst(new Block(ny, nx, head.dir));
			// 사과 -> 1 늘림
			if(map[ny][nx] == SNAKE) break;
			else if(map[ny][nx] != APPLE) {
				Block tail = snake.pollLast();
				map[tail.y][tail.x] = EMPTY;
			}
			map[ny][nx] = SNAKE;
			second++;
		}
		System.out.print(second+1);
	}
}


class Block {
	static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	int y, x, dir;
	
	Block(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
	
	void turn(int dir) {
		if(this.dir==0) {
			if(dir==Dir.L) this.dir = LEFT;
			else this.dir = RIGHT;
		} else if(this.dir==1) {
			if(dir==Dir.L) this.dir = RIGHT;
			else this.dir = LEFT;
		} else if(this.dir==2) {
			if(dir==Dir.L) this.dir = DOWN;
			else this.dir = UP;
		} else {
			if(dir==Dir.L) this.dir = UP;
			else this.dir = DOWN;
		}
	}
}


class Dir {
	static final int L=0, R=1;
	int sec, dir;
	
	Dir(String str) {
		StringTokenizer st = new StringTokenizer(str, " ");
		this.sec = Integer.parseInt(st.nextToken());
		this.dir = st.nextToken().equals("L") ? L : R;
	}
}
