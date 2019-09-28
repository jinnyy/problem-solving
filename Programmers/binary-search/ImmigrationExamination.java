/*
 * [프로그래머스][Level3] 입국심사
 */
class Solution {
    public long solution(int n, int[] times) {
		long max = 0;
		for(int time : times) {
			max = Math.max(max, time);
		}
		long left = 1;
		long right = max * n;
		
		while(left < right) {
			long mid = (left + right) / 2;
			if(isPossible(mid, (long) n, times)) right = mid;
			else left = mid + 1;
		}
		return right;
	}
	
	static boolean isPossible(long time, long n, int[] times) {
		for(int t : times)
			n -= (time / t);
		return n<=0;
	}
}
