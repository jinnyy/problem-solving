/*
 * [프로그래머스][Level2] 쇠막대기
 * https://programmers.co.kr/learn/courses/30/lessons/42585
 */
import java.util.Stack;


class Solution {
    public int solution(String arrangement) {
		Stack<Integer> st = new Stack<Integer>();
		int sum = 0;
		for(int i=0; i<arrangement.length(); i++) {
			char cur = arrangement.charAt(i);
			if(cur == '(') {
				if(st.isEmpty()) st.add(0);
				else st.add(st.peek() + 1);
			} else {
				int top = st.pop();
				if(arrangement.charAt(i-1) == '(') sum += top;
				else sum++;
			}
		}
		
		return sum;
	}
}
