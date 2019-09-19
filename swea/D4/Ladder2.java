/*
 * [SWEA][1211] Ladder 2
 * 32,780 kb  167 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Solution {
	static final int MAX = 100;
	static final int[] dy = {0, 0, 1};
	static final int[] dx = {-1, 1, 0}; // 좌, 우, 아래 순으로 확인

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int test_case=1; test_case<=10; test_case++) {
			// read
			br.readLine();
			int[][] map = new int[MAX][MAX];
			for(int i=0; i<MAX; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<MAX; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// calculate
			int min = 10000;
			int result = -1;
			for(int start=0; start<MAX; start++) {
				if(map[0][start] == 1) {
					int localResult = go(start, map);
					if(min >= localResult) {
						min = localResult;
						result = start;
					}
				}				
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(result);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	static int go(int from, int[][] map) {
		int length = 1;
		int cy = 0, cx = from;
		boolean[][] visited = new boolean[MAX][MAX];
		
		while(true) {
			if(cy == MAX-1) break;
			visited[cy][cx] = true;
			
			boolean moved = false;
			for(int i=0; i<3; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if(ny<0 || nx<0 || ny>=MAX || nx>=MAX) continue;
				if(map[ny][nx] != 0 && !visited[ny][nx]) {
					cy = ny;
					cx = nx;
					length++;
					moved = true;
					break;
				}
			}
			if(!moved) return -1;
		}
		
		return length;
	}

}
