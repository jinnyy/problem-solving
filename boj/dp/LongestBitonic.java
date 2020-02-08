/*
 * [백준] 가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054
 */
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int N, A[], inc[], dec[], max;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N];
		for(int i=0; i<N; i++)
			A[i] = sc.nextInt();
		sc.close();
		
		inc = new int[N+1];
		dec = new int[N+1];
		max = 0;
		// increase: i까지 증가하는 최대 개수
		for(int i=0; i<N; i++) {
			int min=0;
			for(int j=0; j<=i; j++) {
				if(A[j] < A[i]) {
					min = Math.max(min, inc[j]);
				}
			}
			inc[i] = min + 1;
		}
		// decrease: i부터 감소하는 최대 개수
		for(int i=N-1; i>=0; i--) {
			int min = 0;
			for(int j=i; j<N; j++) {
				if(A[i] > A[j]) {
					min = Math.max(min, dec[j]);
				}
			}
			dec[i] = min + 1;
			max = Math.max(max, inc[i] + dec[i]);
		}
		
		System.out.println(max-1);
	}
}
