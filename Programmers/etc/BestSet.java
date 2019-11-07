/*
 * [프로그래머스] 최고의 집합
 * https://programmers.co.kr/learn/courses/30/lessons/12938
 */
 
 class Solution {
    public int[] solution(int n, int s) {
		  if(s < n) return new int[] {-1};
		  int e1 = s / n;
		  int mod = s % n;
		  int[] answer = new int[n];
		  for(int i=0; i<n; i++)
			  answer[i] = e1;
		  for(int i=n-1; i>=n-mod; i--)
			  answer[i]++;
		  return answer;
	  }
}
