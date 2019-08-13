/*
 * [백준][14889] 스타트와 링크
 * 16940KB 328ms
 */
package backTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class StartAndLink {
	static int N, map[][], diff;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		diff = Integer.MAX_VALUE;
		divide(0, new int[N], 0, new int[N], 0);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(String.valueOf(diff));
		bw.flush();
	}

	static void divide(int depth, int[] team1, int cnt1, int[] team2, int cnt2) {
		if(depth==N) {
			// 능력치 계산
			int st1 = stat(team1, cnt1);
			int st2 = stat(team2, cnt2);
			diff = Math.min(diff, Math.abs(st1 - st2));
			return;
		}
		team1[cnt1] = depth;
		divide(depth+1, team1, cnt1+1, team2, cnt2);
		team1[cnt1] = 0;
		team2[cnt2] = depth; 
		divide(depth+1, team1, cnt1, team2, cnt2+1);
		team2[cnt2] = 0;
	}
	
	static int stat(int[] members, int cnt) {
		int result = 0;
		for(int i=0; i<cnt; i++) {
			for(int j=0; j<cnt; j++) {
				result += map[members[i]][members[j]];
			}
		}
		return result;
	}
}
