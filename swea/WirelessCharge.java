/*
 * [SWEA][5644] 무선 충전
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo
 * 21,104KB  144ms
 */
package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class WirelessCharge {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int test_case=1; test_case<=T; test_case++) {
			// M = 이동 시간
			// A = BC의 개수
			int M, A;
			User users[];
			BC chargers[];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			User.A = A;
			users = new User[2];
			chargers = new BC[A];
			for(int i=0; i<2; i++) {
				users[i] = new User(i, M, br.readLine());
			}
			for(int i=0; i<A; i++) {
				chargers[i] = new BC(br.readLine());
			}
			
			// calculate
			// user의 위치를 알고리즘이 바꾸는 것이 아님. (문제에서 고정)
			// 지금 방문하려는 곳에서 얻을 수 있는 최대 충전량을 그때그때 계산
			// 이전/이후 방문이 영향을 미치지 않음
			int total = 0;
			for(int t=0; t<=M; t++) {
				// 1. 유저별로 현재 위치에서 충전가능한 BC들을 찾음
				for(User user : users) {
					for(int i=0; i<A; i++) {
						BC charger = chargers[i];
						if(Math.abs(charger.y-user.y) + Math.abs(charger.x-user.x) <= charger.C) {
							user.chargeable[i] = true;
						}
					}
				}
				
				// 2. 그 BC에 접근가능한 유저들을 따져서 -> 얻을 수 있는 최대 충전량 계산
				int max = 0;
				for(int i=0; i<A; i++) {
					boolean aWillCharge = users[0].chargeable[i];
					// user B - j를 고름
					for(int j=0; j<A; j++) {
						boolean bWillCharge =  users[1].chargeable[j];
						if(aWillCharge && bWillCharge) {
							if(i==j) max = Math.max(max, chargers[i].P);
							else max = Math.max(max, chargers[i].P + chargers[j].P);
						}
						else if(aWillCharge && !bWillCharge) {
							max = Math.max(max, chargers[i].P);
						}
						else if(!aWillCharge && bWillCharge) {
							max = Math.max(max, chargers[j].P);
						}
					}
				}
				
				// 3. total에 더함
				total += max;
				
				for(User user : users) {
					user.move(t);
				}
			}
			
			
			// write
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(total);
			bw.append(sb.toString());
			bw.newLine();
		}
		bw.close();
		br.close();
	}

}


class User {
	static final int[] dy = {0, -1, 0, 1, 0};
	static final int[] dx = {0, 0, 1, 0, -1};
	static int A;
	int num, y, x, dir[];
	boolean[] chargeable;
	
	User(int num, int t, String dirs) {
		this.num = num;
		if(num==0) { this.y=1; this.x=1; }
		else {this.y=10; this.x=10; }
		StringTokenizer st = new StringTokenizer(dirs, " ");
		dir = new int[t+1];
		for(int i=0; i<t; i++) {
			dir[i] = Integer.parseInt(st.nextToken());
		}
		this.chargeable = new boolean[A];
	}
	void move(int time) {
		this.y += dy[dir[time]];
		this.x += dx[dir[time]];
		this.chargeable = new boolean[A];
	}
	
	public String toString() {
		return String.format("(%d, %d)", y, x);
	}
}

class BC {
	int y, x, C, P;
	// C = 충전범위
	// P = 충전량 (성능)
	
	BC(int y, int x, int C, int P) {
		this.y = y;
		this.x = x;
		this.C = C;
		this.P = P;
	}
	
	BC(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");
		this.x = Integer.parseInt(st.nextToken());
		this.y = Integer.parseInt(st.nextToken());
		this.C = Integer.parseInt(st.nextToken());
		this.P = Integer.parseInt(st.nextToken());
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", y, x);
	}
}
