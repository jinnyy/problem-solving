/*
 * [백준] 신나는 함수 실행
 * https://www.acmicpc.net/problem/9184
 */
import java.util.Scanner;

public class Main {
	static int[][][] dp;

	public static void main(String[] args) {
		int a, b, c;
		dp = new int[21][21][21];
		
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		
		while(!(a==-1 && b==-1 && c==-1)) {
			System.out.println(String.format("w(%d, %d, %d) = %d", a, b, c, W(a, b, c)));
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
		}
		sc.close();
	}
	
	public static int W(int a, int b, int c) {
		if(a<=0 || b<=0 || c<=0)
			return 1;
		if(a>20 || b>20 || c>20)
			return W(20, 20, 20);
		if(dp[a][b][c] != 0) return dp[a][b][c];
		if(a<b && b<c)
			return dp[a][b][c] = W(a, b, c-1) + W(a, b-1, c-1) - W(a, b-1, c);
		return dp[a][b][c] = W(a-1, b, c) + W(a-1, b-1, c) + W(a-1, b, c-1) - W(a-1, b-1, c-1);		
	}
}
