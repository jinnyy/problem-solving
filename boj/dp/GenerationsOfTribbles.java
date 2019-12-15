import java.util.Scanner;
/*
 * [백준] Generations of Tribbles
 * https://www.acmicpc.net/problem/9507
 */


public class Main {

	public static void main(String[] args) {
		int t, n[];
		long dp[];
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		n = new int[70];
		for(int i=0; i<t; i++) {
			n[i] = sc.nextInt();
		}
		sc.close();
		
		dp = new long[68];
		int[] temp = {1, 1, 2, 4, 8};
		for(int i=0; i<=4; i++)
			dp[i] = temp[i];
		for(int i=4; i<67; i++) {
			dp[i+1] = 2*dp[i] - dp[i-4];
		}
		
		// Print answers
		for(int i=0; i<t; i++)
			System.out.println(dp[n[i]]);
	}
}


/*
 * p.s.
 * 조금 더 시간을 줄이기 위해 테스트 케이스를 받는 시점에 (테스트 케이스의) 최대값(Max)을 기억해둔 뒤
 * 그 최대값(Max)까지만 루프를 돌면서 dp 값을 계산하는 방법이 가능하다. 
 * 하지만 문제 해결을 위해 필요하지 않고, 시간이 단축되는 효과에 비해 코드가 복잡해져서 그 부분을 구현하지 않았다. 
 */
