import java.util.PriorityQueue;

class Solution {
    public int solution(int[] weight) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        // O(N)
        for(int w : weight){
            pq.add(w);
        }
        int answer = pq.poll();
        if(answer>1) return 1;
        // O(N*logN)
        while(!pq.isEmpty()){
            int cur = pq.poll();
            if(answer+1 >= cur) answer += cur;
            else break;
        }
        return answer+1;
    }
}
