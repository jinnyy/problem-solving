/*
 * [프로그래머스][Level2] 124 나라의 숫자
 * https://programmers.co.kr/learn/courses/30/lessons/12899
 */
class Solution {
    static final int[] NUM = {4, 1, 2};
    static final int DIGIT = 3;
    
     public String solution(int n) {
         String answer = "";
         while(n > 0) {
             int mod = n % DIGIT;
             answer = String.valueOf(NUM[mod]) + answer;
             n /= DIGIT;
             if(mod == 0) n--;
         }
         return answer;
     }
}
