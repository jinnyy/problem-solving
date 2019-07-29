/*
 * [SWEA] 보호필름 (2112번)
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu
 */
package swea;
import java.util.Scanner;


public class ProtectiveFilm {
	static int D, W, K;
	static int[] A, B;

	public static void main(String[] args) {
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int film[][];
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			film = new int[D][W];
			for(int i=0; i<D; i++) {
				for(int j=0; j<W; j++) {
					film[i][j] = sc.nextInt();
				}
			}
			A = new int[W];
			B = new int[W];
			for(int i=0; i<W; i++) B[i]=1;
			int answer;
			for(answer=0; answer<D; answer++) {
				// 약품 투약
				// [어떤 층]을 [어떤 종류]로 바꿀 것인가
				// 검사 -> 통과시 break;
				if(makeFilm(film, answer, 0, D-1)) break;
			}
			// print
			System.out.println(String.format("#%d %d", test_case, answer>D? D: answer));
		}
		sc.close();
	}
	
	// start=시작하는깊이, end=끝나는깊이
	private static boolean makeFilm(int[][] film, int amount, int start, int end) {
		if(amount==0) return test(film);
		if(start==end) return false;
		int[] cache;
		boolean ret;
		
		for(int s=start; s<end; s++) {
			cache = film[s];
			film[s] = A;
			ret = makeFilm(film, amount-1, s+1, end);
			film[s] = B;
			ret = ret || makeFilm(film, amount-1, s+1, end);
			film[s] = cache;
			if(ret) return ret;
		}
		
		cache = film[end];
		film[end] = A;
		ret = makeFilm(film, amount-1, start, end-1);
		film[end] = B;
		ret = ret || makeFilm(film, amount-1, start, end-1);
		film[end] = cache;
		
		return ret;
	}

	private static boolean test(int[][] film) {
		boolean pass = true;
		for(int j=0; j<W; j++) {
			int cont = 1;
			for(int i=1; i<D; i++) {
				if(film[i-1][j] == film[i][j])
					cont++;
				else cont = 1;
				if(cont >= K) break;
			}
			if(cont<K) {
				return false;
			}
		}
		return pass;
	}
}
