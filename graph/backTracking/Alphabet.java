/*
 * [백준][1987번] 알파벳
 * 13732KB 760ms
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Alphabet {
	static int R, C, map[][], max;
	static final int[] dy = {0, 0, 1, -1};
	static final int[] dx = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j) - 'A';
			}
		}
		
		// 연산
		max = 1;
		dfs(0, 0, new boolean[26], max);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(max);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(sb.toString());
		bw.newLine();
		bw.flush();
	}

	static void dfs(int cy, int cx, boolean[] visited, int count) {
		visited[map[cy][cx]] = true;
		boolean ableToVisit = false;
		for(int i=0; i<4; i++) {
			int y = cy + dy[i];
			int x = cx + dx[i];
			if(y<0 || x<0 || y>=R || x>=C) continue;
			if(!visited[map[y][x]]) {
				ableToVisit = true;
				dfs(y, x, visited, count+1);
			}
		}
		if(!ableToVisit) 
			max = Math.max(max, count);
		visited[map[cy][cx]] = false;
	}
}
