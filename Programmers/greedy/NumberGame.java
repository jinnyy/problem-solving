/*
 * [프로그래머스][Level3] 숫자 게임
 * https://programmers.co.kr/learn/courses/30/lessons/12987
 */
import java.util.Arrays;


class Solution {
    public int solution(int[] A, int[] B) {
		Arrays.sort(A);
		Arrays.sort(B);
		int answer = 0;
		int a = A.length-1;
		int b = B.length-1;
		while(a >= 0 && b>=0) {
			if(A[a] < B[b]) {
				answer++;
				b--;
			}
			a--;
		}
		return answer;
	}
}
