import java.util.Scanner;
import java.util.Arrays;


public class Main {
	static int N, M, num[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = sc.nextInt();
		}
		sc.close();
		
		Arrays.sort(num);
		make(0, -1, new int[M], new boolean[N]);
	}

	private static void make(int depth, int cur, int[] seq, boolean[] visited) {
		if(depth == M) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<M; i++) {
				sb.append(seq[i]);
				sb.append(" ");
			}
			System.out.println(sb.toString());
			return;
		}
		
		for(int next=cur+1; next<N; next++) {
			if(visited[next]) continue;
			seq[depth] = num[next];
			visited[next] = true;
			make(depth+1, next, seq, visited);
			seq[depth] = 0;
			visited[next] = false;
		}
	}
}
