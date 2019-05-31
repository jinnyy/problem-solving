package dp;
/*
 * [백준] 극장 좌석
 * https://www.acmicpc.net/problem/2302
 */
import java.util.Scanner;
import java.util.LinkedList;


public class TheaterSeat {

	public static void main(String[] args) {
		int N, M, dp[];
		LinkedList<Integer> cont = new LinkedList<Integer>(); 
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		dp = new int[41];
		
		for(int i=0; i<M; i++) {
			dp[sc.nextInt()] = -1;
		}
		sc.close();
		
		int max = 0;
		int length = 0;
		for(int i=1; i<=N; i++) {
			if(dp[i]==-1) {
				cont.add(length);
				max = Math.max(max, length);
				length = 0;
			}
			else {
				length++;
				if(i==N) {
					cont.add(length);
					max = Math.max(max, length);
				}
			}
		}
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2; i<=max; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		int ans = 1;
		while(!cont.isEmpty()) {
			ans *= dp[cont.pop()];
		}
		System.out.println(ans);
	}

}
