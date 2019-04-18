package dp;
/*
 * [백준] 파도반 수열
 * https://www.acmicpc.net/problem/9461
 */
import java.util.Scanner;


public class WaveSequence {

	public static void main(String[] args) {
		int T, N[], max;
		long P[];
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		P = new long[101];
		N = new int[T];
		max = 0;
		for(int i=0; i<T; i++) {
			N[i] = sc.nextInt();
			max = Math.max(max, N[i]);
		}
		sc.close();
		
		for(int i=1; i<=3; i++)
			P[i]=1;
		for(int i=4; i<=5; i++)
			P[i]=2;
		for(int i=6; i<=max; i++) {
			P[i] = P[i-1] + P[i-5];
		}
		for(int n: N) {
			System.out.println(P[n]);
		}
	}

}
