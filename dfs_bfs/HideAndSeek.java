package dfs_bfs;
/*
 * [백준] 숨바꼭질
 * 
 */
import java.util.Scanner;
import java.util.LinkedList;


public class HideAndSeek {
	static final int MAX = 100000;

	public static void main(String[] args) {
		int N, K, loc[];
		LinkedList<Integer> Q;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sc.close();
		
		// 동생은 고정. 수빈이는 +1, -1, *2
		loc = new int[MAX+1];
		Q = new LinkedList<Integer>();
		Q.add(N);
		loc[N] = 1;
		int cur;
		while(!Q.isEmpty()) {
			cur = Q.poll();
			if(cur==K) break;
			if(cur-1>=0 && loc[cur-1]==0) {
				Q.add(cur-1);
				loc[cur-1] = loc[cur]+1;
			}
			if(cur+1<=MAX && loc[cur+1]==0) {
				Q.add(cur+1);
				loc[cur+1] = loc[cur]+1;
			}
			if(cur*2<=MAX && loc[cur*2]==0) {
				Q.add(cur*2);
				loc[cur*2] = loc[cur]+1;
			}
		}
		System.out.println(loc[K]-1);
	}

}
