/*
 * [프로그래머스][Level3] 하노이의 탑
 * https://programmers.co.kr/learn/courses/30/lessons/12946
 */
import java.util.LinkedList;


class Solution {
    static LinkedList<int[]> route;
    
    public int[][] solution(int n) {
    	route = new LinkedList<int[]>();
    	move(n, 1, 2, 3);
        int[][] answer = new int[route.size()][2];
        for(int i=0; i<answer.length; i++) {
        	answer[i] = route.poll();
        }
        return answer;
    }
    
    static void move(int depth, int from, int by, int to) {
        if(depth == 1){
            route.add(new int[] {from, to});
            return;
        }
        move(depth-1, from, to, by);	// [1] n-1개를 				from-> by
        move(1, from, by, to);		// [2] 가장 밑에 있는 1개를		from-> to
        move(depth-1, by, from, to);	// [3] ([1]에서 by번으로 간) n-1개를	by-> to
    }
}
