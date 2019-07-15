/*
 * [프로그래머스] [DFS/BFS] 타겟 넘버
 * https://programmers.co.kr/learn/courses/30/lessons/43165 
 */
package Programmers;



class TargetNumber {
	static int count;
    public int solution(int[] numbers, int target) {
    	count = 0;
    	dfs(numbers, target, 0, 0);
        return count;
    }
    
    private static void dfs(int[] numbers, int target, int depth, int curResult) {
    	// Base condition
    	if(depth==numbers.length-1) {
    		if(curResult+numbers[depth]==target) count++;
    		if(curResult-numbers[depth]==target) count++;
    		return;
    	}
	// 가능한 경우(+,-) depth를 1 증가시켜서 recursion
    	dfs(numbers, target, depth+1, curResult-numbers[depth]);
    	dfs(numbers, target, depth+1, curResult+numbers[depth]);
    }
}
