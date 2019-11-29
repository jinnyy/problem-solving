/*
 * [백준] 상자넣기
 * https://www.acmicpc.net/problem/1965
 */
import java.util.Scanner;


// cf. '가장 긴 증가하는 부분 수열'과 같은 원리로 풀 수 있다.
public class Main {

	public static void main(String[] args) {
		int n, box[], dp[], max;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		box = new int[n];
		for(int i=0; i<n; i++)
			box[i] = sc.nextInt();
		sc.close();
		
		dp = new int[n];
		dp[0] = 0;
		max=0;
		for(int i=1; i<n; i++) {
			int min=-1;
			for(int j=0; j<i; j++) {
				if(box[j] < box[i] && dp[j] >= dp[min==-1? 0: min]) {
					min = j;
				}
			}
			if(min==-1) continue;
			dp[i] = dp[min]+1;
			max = Math.max(max, dp[i]);
		}
		System.out.println(max+1);
	}

}
