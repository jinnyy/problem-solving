/*
 * [백준] 회사에 있는 사람
 * https://www.acmicpc.net/problem/7785
 */
package dataStructure;
import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;


public class PeopleInCompany {
	public static void main(String[] args) {
		int N;
		String[] log;
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		HashMap<String, String> people = new HashMap<String, String>();
		for(int i=0; i<N; i++) {
			log = sc.nextLine().split(" ");
			if(log[1].equals("enter")) {
				people.put(log[0], log[1]);
			} else if(log[1].equals("leave")) {
				people.remove(log[0]);
			}
		}
		PriorityQueue<String> Q = new PriorityQueue<String>(100001, new NameComparator());
		for(String person: people.keySet()) {
			Q.add(person);
		}
		while(!Q.isEmpty()) {
			System.out.println(Q.poll());
		}
		sc.close();
	}
}

class NameComparator implements Comparator<String> {
	@Override
	public int compare(String arg0, String arg1) {
		return arg1.compareTo(arg0);
	}
}
