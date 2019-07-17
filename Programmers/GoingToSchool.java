/*
 * [프로그래머스][DP] 등굣길
 * https://programmers.co.kr/learn/courses/30/lessons/42898
 */
package programmers;


/*
 * map에 웅덩이 정보를 표시
 * - calculate 부분 코드가 복잡해짐
 * - 메모리를 적게 사용
 */
public class GoingToSchool {
	static final int MOD = 1000000007;
	

	public static int solution(int m, int n, int[][] puddles) {
		int[][] map = new int[n+1][m+1];
		for(int[] puddle: puddles) {
			map[puddle[1]][puddle[0]] = -1;
		}
		map[1][0] = 1;
		
		// calculate
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(map[i][j]==-1) continue;
				if(map[i-1][j]>0) {
					map[i][j] += map[i-1][j];
					map[i][j] %= MOD;
				}
				if(map[i][j-1]>0) {
					map[i][j] += map[i][j-1]; 
					map[i][j] %= MOD;
				}
			}
		}
		return map[n][m]<0? 0: map[n][m];
	}
}
