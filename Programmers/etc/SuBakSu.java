/*
 * [프로그래머스][Level1] 수박수박수박수박수박수?
 * https://programmers.co.kr/learn/courses/30/lessons/12922
 */

class Solution {
  public String solution(int n) {
      final String[] pattern = {"수", "박"};
      StringBuilder answer = new StringBuilder();
      for(int i=0; i<n; i++) {
          answer.append(pattern[i%2]);
      }
      return answer.toString();
  }
}
