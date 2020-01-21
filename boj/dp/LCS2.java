/*
 * [백준] LCS2
 * https://www.acmicpc.net/problem/9252
 */
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		int N, M;
		String str[], cache[][];
		str = new String[2];
		Scanner sc = new Scanner(System.in);
		str[0] = sc.nextLine().trim();
		str[1] = sc.nextLine().trim();
		sc.close();
		
		N = str[0].length();
		M = str[1].length();
		cache = new String[N+1][M+1];
		
    		// 계산 시작
		for(int i=1; i<=N; i++) {
			char a, b;
			for(int j=1; j<=M; j++) {
				a = str[0].charAt(i-1);
				b = str[1].charAt(j-1);
				if(a==b) {
					cache[i][j] = (cache[i-1][j-1]==null? "": cache[i-1][j-1]) + a;
				}
				else {
					int n = cache[i-1][j]==null? 0: cache[i-1][j].length();
					int m = cache[i][j-1]==null? 0: cache[i][j-1].length();
					if(n > m) cache[i][j] = cache[i-1][j];
					else cache[i][j] = cache[i][j-1];
				}
			}
		}
		String result = cache[N][M];
		// cf. 2019-05-28 현재 기준
		//     공통 부분 수열이 없는 테스트 케이스는 없다
		if(result==null) {
			System.out.println(0);
			System.out.println("");
		}
		else {
			System.out.println(result.length());
			System.out.println(result);
		}
	}

}
