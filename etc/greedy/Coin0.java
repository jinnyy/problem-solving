/*
 * [백준] 동전0
 * https://www.acmicpc.net/problem/11047
 */
package greedy;
import java.util.Scanner;


public class Coin0 {

	public static void main(String[] args) {
		int N, goal, coins[];
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		goal = sc.nextInt();
		coins = new int[N];
		for(int i=N-1; i>=0; i--)
			coins[i] = sc.nextInt();
		sc.close();
		
		int cnt = 0;
		for(int coin: coins) {
			while(true) {
				if(goal < coin) break;
				goal -= coin;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
