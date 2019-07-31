/*
 * [백준] 터렛
 * https://www.acmicpc.net/problem/1002
 */
package etc;
import java.util.Scanner;


// 원끼리 접점이 몇 개인지 구하는 문제이다.
// 내접, 외접, 일치 등의 경우가 있고, 그 외의 경우는 거리를 이용해 구할 수 있다.
public class Turret {

	public static void main(String[] args) {
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int x1, y1, x2, y2;
			double r1, r2, dist;
			x1 = sc.nextInt();
			y1 = sc.nextInt();
			r1 = sc.nextInt();
			x2 = sc.nextInt();
			y2 = sc.nextInt();
			r2 = sc.nextInt();
			if(x1==x2 && y1==y2) {
				if(r1==r2) System.out.println(-1);
				else System.out.println(0);
				continue;
			}
			dist = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
			if(dist == Math.abs(r1-r2) || dist == r1+r2) System.out.println(1);
			else if(dist < Math.abs(r1-r2) || dist > r1+r2) System.out.println(0);
			else System.out.println(2);
		}
		sc.close();
	}
}
