/*
 * [프로그래머스][Level2] 더 맵게
 * https://programmers.co.kr/learn/courses/30/lessons/42626
 */
import java.util.PriorityQueue;


class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int food : scoville){
            pq.add(food);
        }
        int answer = 0;
        while(pq.peek() < K && pq.size()>1){
            int min = pq.poll();
            int next = pq.poll();
            pq.add(min + (2 * next));
            answer++;
        }
        if(pq.peek()<K) return -1;
        return answer;
    }
}
