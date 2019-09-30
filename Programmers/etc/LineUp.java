/*
 * [프로그래머스][Level3] 줄 서는 방법
 * https://programmers.co.kr/learn/courses/30/lessons/12936
 */
import java.util.ArrayList;

class Solution {
  public int[] solution(int n, long k) {
    	int[] answer = new int[n];
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	long mod = 1;
    	for(int i=1; i<=n; i++) {
    		list.add(i);
    		mod *= i;
    	}
    	k--;
    	for(int idx=0; idx<n; idx++) {
    		mod /= (n-idx);
    		int nIdx = (int) (k / mod);
    		k %= mod;
    		int number = list.get(nIdx);
    		list.remove(nIdx);
    		answer[idx] = number;
    	}
    	
        return answer;
    }
}
