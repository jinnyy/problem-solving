package dfs_bfs;
/*
 * [백준] 안전 영역
 * https://www.acmicpc.net/problem/2468
 */
import java.util.Scanner;



public class SafeZone {
	static int N, MaxLevel, MinLevel, graph[][], count[], group[][];
	static boolean visited[][];
	static final int dX[] = {1, -1, 0, 0};
	static final int dY[] = {0, 0, 1, -1};
	

	public static void main(String[] args) {
		MaxLevel = 0;
		MinLevel = Integer.MAX_VALUE;
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		graph = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				graph[i][j] = reader.nextInt();
				MaxLevel = Math.max(graph[i][j], MaxLevel);
				MinLevel = Math.min(graph[i][j], MinLevel);
			}
		}
		reader.close();
		count = new int[MaxLevel+1];
		
		
		// 0 ~ Max 에 dfs()
		int maxCount = 1;
		for(int i=MinLevel; i<MaxLevel; i++) {
			dfs(i);
			maxCount = Math.max(maxCount, count[i]);
		}
		System.out.println(maxCount);
	}
	
	
	static void dfs(int level) {
		visited = new boolean[N][N];
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(!visited[y][x] && graph[y][x]>level) {
					++count[level];
					doDfs(y, x, level);
				}
			}
		}
	}

	private static void doDfs(int curY, int curX, int level) {
		visited[curY][curX] = true;
		
		int x, y;
		for(int i=0; i<dX.length; i++) {
			x = curX + dX[i];
			y = curY + dY[i];
			
			if(x>=0 && x<N && y>=0 && y<N) {
				if(!visited[y][x] && graph[y][x]>level) {
					doDfs(y, x, level);
				}
			}
		}
	}
}

