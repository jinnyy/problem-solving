package dfs_bfs;
/*
 * [백준] 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class NumberingComplex {
	static int N, complexCount, localCount, graph[][];
	static ArrayList<Integer> houseCount;
	static boolean[][] visited;
	static final int[] X = {-1, 1, 0, 0};
	static final int[] Y = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		N = reader.nextInt();
		graph = new int[N+1][N+1];
		for(int i=0; i<N; i++) {
			String line = reader.next();
			for(int j=0; j<N; j++)
				graph[j][i] = line.charAt(j) - '0';
		}
		reader.close();
		
		// dfs
		dfs();
		System.out.println(complexCount);
		
		// sorting
		Collections.sort(houseCount);
		for(int i=0; i<houseCount.size(); i++)
			System.out.println(houseCount.get(i));
		
	}
	private static void doDfs(int y, int x) {
		visited[y][x] = true;
		localCount++;
		int curX, curY;
		for(int i=0; i<4; i++) {
			curX = x + X[i];
			curY = y + Y[i];
			if (curX<0 || curX>=N || curY<0 || curY>=N) continue;
			if (graph[curY][curX]==1 && !visited[curY][curX]) {
				doDfs(curY, curX);
			}
		}
	}
	private static void dfs() {
		visited = new boolean[N][N];
		complexCount = 0;
		houseCount = new ArrayList<Integer>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[j][i] && graph[j][i]==1) {
					doDfs(j, i);
					complexCount++;
					houseCount.add(localCount);
					localCount = 0;
				}
			}
		}
	}
}
