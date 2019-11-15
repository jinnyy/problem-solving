/*
 * [프로그래머스][Level2] 탑
 * https://programmers.co.kr/learn/courses/30/lessons/42588
 */
import java.util.Stack;


class Solution {
    static final int MAX = 101;
    

	public int[] solution(int[] heights) {
		int N = heights.length;
		int[] answer = new int[N];
		Stack<Tower> st = new Stack<Tower>();
		st.add(new Tower(0, MAX));
		for(int i=0; i<N; i++) {
			while(!st.isEmpty()) {
				Tower right = st.pop();
				if(right.height > heights[i]) {
					answer[i] = right.name;
					st.add(right);
					break;
				}
			}
			st.add(new Tower(i+1, heights[i]));
		}
		return answer;
	}
	
	class Tower {
		int name, height;
		
		Tower(int name, int height){
			this.name = name;
			this.height = height;
		}
	}
}
