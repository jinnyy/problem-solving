/*
 * [SWEA][2383] 점심 식사시간
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
 */
package swea;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class LunchTime {
	static int pNum, min;
	static Person People[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int N, map[][];
			N = sc.nextInt();
			map = new int[N][N];
			pNum = 0;
			People = new Person[10];
			Stair[] stairs = new Stair[2];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j]==1) 
						People[pNum++] = new Person(i, j);
					if(map[i][j]>1) {
						if(stairs[0]==null) stairs[0] = new Stair(i, j, map[i][j]);
						else stairs[1] = new Stair(i, j, map[i][j]);
					}
				}
			}
			// 계산 시작
			// 1. P를 S에 분배 (dfs)
			// 2. dfs의 base case에서 각 S에서 계단 내려가는 총 시간 계산
			min = Integer.MAX_VALUE;
			dfs(0, stairs);
			// print
			System.out.println(String.format("#%d %d", test_case, min));
		}
		sc.close();
	}
	
	static void dfs(int depth, Stair[] S) {
		if(depth==pNum) {
			// 연산 시작
			int result = go(S);
			min = Math.min(min, result);
			return;
		}
		// 현재 번호 사람을 S1에 넣는 경우
		S[0].addPerson(People[depth]);
		dfs(depth+1, S);
		S[0].Q.pollLast();
		// 현재 번호 사람을 S2에 넣는 경우
		S[1].addPerson(People[depth]);
		dfs(depth+1, S);
		S[1].Q.pollLast();
	}
	
	static int go(Stair[] stairs) {
		int timeMax = 0;
		// 1. dist 순으로 줄세우기
		// 2. 계단을 내려가는 중인지 표시 (dist--)
		// 3. 계단을 다 내려갔다면 Q에 다시 넣지 않는다.
		for(Stair stair: stairs) {
			int time = 0;
			// 1. 줄세우기
			PriorityQueue<Person> pq = new PriorityQueue<Person>();
			LinkedList<Person> q = new LinkedList<Person>();
			LinkedList<Person> goingP = new LinkedList<Person>();
			for(Person p: stair.Q) {
				Person newp = new Person(p.y, p.x);
				newp.dist = p.dist;
				pq.add(newp);
			}
			int n = pq.size();
			for(int i=0; i<n; i++) {
				q.add(pq.poll());
			}
			
			// 2. 이동
			while(!(q.isEmpty() && goingP.isEmpty())) {
				LinkedList<Person> q2 = new LinkedList<Person>();
				
				int gsize = goingP.size();
				for(int i=0; i<gsize; i++) {
					Person gp = goingP.poll();
					gp.stairTime++;
					if(gp.stairTime<stair.size) goingP.add(gp);
				}
				
				while(!q.isEmpty()) {
					Person cur = q.poll();
					cur.dist--;
					if(cur.dist<=0 && goingP.size()<3) goingP.add(cur);
					else q2.add(cur);
				}
				q = q2;
				time++;
			}
			timeMax = Math.max(timeMax, time);
		}
		return timeMax;
	}
}


class Stair {
	int y, x, size;
	LinkedList<Person> Q;
	
	Stair(int y, int x, int size) {
		this.y = y;
		this.x = x;
		this.size = size;
		this.Q = new LinkedList<Person>();
	}
	
	void addPerson(Person p) {
		Q.add(p);
		p.distance(this);
		p.stairTime = 0;
	}
}

class Person implements Comparable<Person> {
	int y, x, dist, stairTime;
	
	Person(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	void distance(Stair s) {
		this.dist = Math.abs(this.y - s.y) + Math.abs(this.x - s.x) + 1;
	}
	
	@Override
	public int compareTo(Person p) {
		return this.dist - p.dist;
	}
}
