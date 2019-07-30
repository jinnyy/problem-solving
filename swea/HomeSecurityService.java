/*
 * [SWEA] 홈 방범 서비스 (2117)
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu
 */
package swea;
import java.util.Scanner;


public class HomeSecurityService {
	static boolean[][] map;
	static int max, costs[];

	public static void main(String[] args) {
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		costs = new int[25];
		for(int test_case=1; test_case<=T; test_case++) {
			// N: 도시의 크기
			// M: 하나의 집이 지불할 수 있는 비용
			int N, M;
			N = sc.nextInt();
			M = sc.nextInt();
			map = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(sc.nextInt()==1) map[i][j]=true;
				}
			}
			
			int K = N+1;
			max = 0;
			while(K>=1) {
				for(int i=-(K/2); i<N; i++) {
					for(int j=0; j<N; j++) {
						max = Math.max(max, profit(i, j, K, M, N));
					}
				}
				K--;
			}
			System.out.println(String.format("#%d %d", test_case, max));
		}
		sc.close();
		
	}
	// cy, cx에서 시작해서 마름모 모양으로 K만큼 탐색하는 함수
	private static int profit(int cy, int cx, int K, int M, int N) {
		int house = 0;
		// 범위에 house가 몇 개 있는지 센다.
		// 마름모 모양으로 보면서 -> IF(map[i][j]) THEN house++;
		
		// 1) 늘어나는 구간
		for(int row=1; row<2*K-1; row+=2) {
			int y, x; // 시작점
			y = cy;
			x = cx;
			for(int i=0; i<row; i++) {
				if(y>=N || y<0 || x+i>=N || x+i<0) continue;
				if(map[y][x+i]) house++;
			}
			cy++;
			cx--;
		}
		
		// 2) 줄어드는 구간
		for(int row=K*2-1; row>=1; row-=2) {
			int y, x; // 시작점
			y = cy;
			x = cx;
			for(int i=0; i<row; i++) {
				if(y>=N || y<0 || x+i>=N || x+i<0) continue;
				if(map[y][x+i]) house++;
			}
			cy++;
			cx++;
		}
		return M*house >= cost(K)? house: -1;
	}
	
	private static int cost(int K) {
		if(costs[K]!=0) return costs[K];
		return costs[K] = 2*K*K -2*K +1;
	}
}
