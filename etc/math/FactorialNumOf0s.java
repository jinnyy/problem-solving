/*
 * [백준][1676] 팩토리얼 0의 개수
 * 14252 KB	108 ms
 */
package etc;
import java.util.Scanner;


public class FactorialNumOf0s {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		int res = 0;
		// 5의 배수가 항상 2의 배수보다 적기 때문에 5(의 배수)의 개수만 구한다.
		for (int i=5; i<=N; i*=5) {
			res += N / i;
		}
		System.out.print(res);
	}
}
