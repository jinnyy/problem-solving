import java.util.Scanner;
/*
 * [백준] 욕심쟁이 판다
 * - dfs
 */


public class GreedyPanda_v1 {
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {1, -1, 0, 0};
	static int N, trees[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		trees = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				trees[i][j] = sc.nextInt();
		}
		sc.close();
		
		// calculation
		int K = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				boolean[][] visited = new boolean[N][N];
				K = Math.max(K, dfs(i, j, visited, 1));
			}
		}
		System.out.println(K);
	}
	
	static int dfs(int cy, int cx, boolean[][] visited, int k) {
		visited[cy][cx] = true;
		int y, x, max=k;
		for(int i=0; i<4; i++) {
			y = cy + dy[i];
			x = cx + dx[i];
			if(y>=0 && x>=0 && y<N && x<N && !visited[y][x] && trees[cy][cx]<trees[y][x]) {
				max = Math.max(max, dfs(y, x, visited, k+1));
			}
		}
		return max;
	}

}
