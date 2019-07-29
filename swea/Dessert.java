package swea;
import java.util.Scanner;


public class Dessert {
	static final int[] dx = {1, 1, -1, -1};
	static final int[] dy = {-1, 1, -1, 1};
	static int N, map[][], result;

	public static void main(String[] args) {
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int test_case=1; test_case<=T; test_case++) {
			int maxDessert=0;
			N = sc.nextInt();
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
					maxDessert = Math.max(maxDessert, map[i][j]);
				}
			}
			
			// 연산 시작
			result = -1;
			boolean[][] visited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dfs(i, j, visited, i, j, new boolean[maxDessert+1], 0, 0, 0, 0);
				}
			}
			// print
			System.out.println(String.format("#%d %d", test_case, result));
		}
		sc.close();
	}
	
	static void dfs(int cy, int cx, boolean[][] visited, int startY, int startX, boolean[] ateDesserts, int cnt, int yDir, int xDir, int changedDir) {
		if(cy==startY && cx==startX && visited[cy][cx]) {
			result = Math.max(result, cnt);
			return;
		}
		visited[cy][cx] = true;
		ateDesserts[map[cy][cx]] = true;
		for(int i=0; i<4; i++) {
			int y = cy + dy[i];
			int x = cx + dx[i];
			if(y<0 || y>=N || x<0 || x>=N) continue;
			// 사각형 모양으로 돌아야 하므로 direction을 몇 번 바꿨는지를 체크한다. 3번 이하로 돌고 start 지점으로 돌아가야 한다.
			if((!visited[y][x] && !ateDesserts[map[y][x]]) || (y==startY && x==startX && cnt>2)) {
				if((dx[i]==xDir && dy[i]==yDir) || (xDir==0 && yDir==0))
					dfs(y, x, visited, startY, startX, ateDesserts, cnt+1, dy[i], dx[i], changedDir);
				else if(changedDir<3)
					dfs(y, x, visited, startY, startX, ateDesserts, cnt+1, dy[i], dx[i], changedDir+1);
			}
		}
		visited[cy][cx] = false;
		ateDesserts[map[cy][cx]] = false;
	}
}
