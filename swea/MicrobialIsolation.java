/*
 * [SWEA] 미생물 격리 (2382)
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl
 */
package swea;
import java.util.Scanner;
import java.util.LinkedList;


public class MicrobialIsolation {

	public static void main(String[] args) {
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int N, M, K; // N=셀의개수, M=격리시간, K=미생물 군집 개수
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			Microbio[][] map = new Microbio[N][N];
			Microbio.N = N;
			for(int i=0; i<K; i++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				Microbio bio = new Microbio(y, x, sc.nextInt(), sc.nextInt());
				map[y][x] = bio;
			}
			
			// 계산
			Microbio[][] curMap, nextMap;
			nextMap = map;
			for(int time=1; time<=M; time++) {
				// 1) 경계에 닿았을 때 -> onChem()
				// 2) 두 개 이상 Microbio가 충돌 -> meet()
				
				curMap = nextMap;
				nextMap = new Microbio[N][N];
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(curMap[i][j]==null) continue;
						Microbio me = curMap[i][j];
						me.move();
						if(nextMap[me.y][me.x] != null) {
							me = nextMap[me.y][me.x].meet(me);
						}
						nextMap[me.y][me.x] = me;
					}
				}
			}
			int sum = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(nextMap[i][j]==null) continue;
					sum += nextMap[i][j].size;
				}
			}
			// print
			System.out.println(String.format("#%d %d", test_case, sum));
		}
		sc.close();
	}

}


class Microbio {
	static int N;
	int y, x, size, dir;
	LinkedList<Microbio> met;
	
	Microbio(int y, int x, int size, int dir) {
		this.y = y;
		this.x = x;
		this.size = size;
		this.dir = dir;
		this.met = new LinkedList<Microbio>();
	}
	
	// for debugging
	public String toString() {
		return String.format("<%d, (%d, %d), %d>", size, y, x, dir);				
	}
	
	void move() {
		switch(this.dir) {
		case 1:
			this.y--;
			if(y==0) this.onChem();
			break;
		case 2:
			this.y++;
			if(y==N-1) this.onChem();
			break;
		case 3:
			this.x--;
			if(x==0) this.onChem();
			break;
		case 4:
			this.x++;
			if(x==N-1) this.onChem();
			break;
		}
		this.met = new LinkedList<Microbio>();
	}
	
	void onChem() {
		switch(this.dir) {
			case 1:
				this.dir = 2;
				break;
			case 2:
				this.dir = 1;
				break;
			case 3:
				this.dir = 4;
				break;
			case 4:
				this.dir = 3;
				break;
		}
		this.size /= 2;
	}
	
	Microbio meet(Microbio B) {
		int max=B.size, direction=B.dir;
		
		// this 제외하고 met에 있는 정보와 Bio를 비교
		// Microbio[] met: 새로운 칸으로 move()하기 전까지 만난 미생물들의 정보
		if(this.met.isEmpty()) this.met.add(this);
		for(Microbio bio: this.met) {
			if(max < bio.size) {
				max = bio.size;
				direction = bio.dir;
			}
		}
		this.met.add(B);
		
		Microbio ret = new Microbio(this.y, this.x, this.size+B.size, direction);
		ret.met = this.met;
		return ret;
	}
}
