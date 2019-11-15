/*
 * [백준] 잃어버린 괄호
 * https://www.acmicpc.net/problem/1541
 */
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		String expr;
		Scanner sc = new Scanner(System.in);
		expr = sc.next();
		sc.close();
		
		int result = 0;
		String num = "";
		boolean flag = false;
		// 처음 '-'가 나온 후에 등장하는 숫자는 모두 빼준다.
		for(int i=0; i<expr.length(); i++) {
			char term = expr.charAt(i);
			if(term=='-' || term=='+') {
				if(flag) result -= Integer.parseInt(num);
				else if(term=='-') {
					result += Integer.parseInt(num);
					flag = true;
				} 
				else result += Integer.parseInt(num);
				num = "";
			}
			else num += term;
		}
		if(flag) result -= Integer.parseInt(num);
		else result += Integer.parseInt(num);
		
		System.out.println(result);
	}

}
