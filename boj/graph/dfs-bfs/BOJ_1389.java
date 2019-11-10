/*
 * [백준][1389] 케빈 베이컨의 6단계 법칙
 * 13252 KB	84 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;


public class Main {
	static int N, M;
	static User[] users;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		users = new User[N+1];
		for(int i=1; i<=N; i++)
			users[i] = new User();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			users[A].friends.add(B);
			users[B].friends.add(A);
		}
		br.close();
		
		int answer = 0;
		int min = 500000;
		for(int user=1; user<=N; user++) {
			int localSum = bfs(user);
			if(localSum < min) {
				min = localSum;
				answer = user;
			}
		}
		System.out.print(answer);
	}
	
	static int bfs(int start) {
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.add(start);
		int[] dist = new int[N+1];
		dist[start] = 1;
		
		while(!Q.isEmpty()) {
			int cur = Q.poll();
			
			for(int next : users[cur].friends) {
				if(dist[next] > 0) continue;
				dist[next] = dist[cur] + 1;
				Q.add(next);
			}
		}
		
		int sum = 0;
		for(int i=1; i<=N; i++)
			sum += (dist[i] - 1);
		return sum;
	}
}


class User {
	LinkedList<Integer> friends;
	
	User(){
		friends = new LinkedList<Integer>();
	}
}
