package dp;
/*
 * ºØ¾î»§ ÆÇ¸Å
 * https://www.acmicpc.net/problem/11052
 */

import java.util.Scanner;


public class FishBun {
	public static int[] price;

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		int N = Integer.parseInt(keyboard.nextLine());
		price = new int[N];
		String[] tempStr = keyboard.nextLine().split(" ");
		price[0] = Integer.parseInt(tempStr[0]);
		keyboard.close();
		
		for(int i=1; i<N; i++) {
			price[i] = Integer.parseInt(tempStr[i]);
			for(int j=0; j<i; j++)
				price[i] = Math.max(price[i], price[j]+price[i-j-1]);
		}
		
		System.out.println(price[price.length-1]);
	}

}
