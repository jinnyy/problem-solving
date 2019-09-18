/*
 * [프로그래머스][level3] 디스크 스케줄러
 * (스케줄링 방법) SJF
 */
import java.util.PriorityQueue;


class Solution {
	
	public int solution(int[][] jobs) {
		PriorityQueue<Job> all = new PriorityQueue<Job>();
		for(int[] job : jobs) {
			all.add(new Job(job[0], job[1]));
		}
		PriorityQueue<Job> Q = new PriorityQueue<Job>();
		int time = 0;
		int waitTime = 0;
		while(!all.isEmpty() || !Q.isEmpty()) {
			Job cur = all.poll();
			while(!all.isEmpty() && cur.start <= time) {
				cur.inQ = true;
				cur.wait += (time - cur.start);
				Q.add(cur);
				cur = all.poll();
			}
			if(cur != null) {
				if(cur.start > time) all.add(cur);
				else {
					cur.inQ = true;
					cur.wait += (time - cur.start);
					Q.add(cur);
				}
			}
			
			if(!Q.isEmpty()) {
				cur = Q.poll();
				time += cur.workTime;
				waitTime += (cur.wait + cur.workTime);
				for(Job job : Q) {
					job.wait += cur.workTime;
				}
			} else {
				time++;
			}
		}
		
		return waitTime / jobs.length;
	}

}

class Job implements Comparable<Job> {
	int workTime, wait, start;
	boolean inQ;
	
	Job(int start, int time) {
		this.start = start;
		this.workTime = time;
		this.wait = 0;
		this.inQ = false;
	}
	
	@Override
	public int compareTo(Job job) {
		if(this.inQ && job.inQ) return this.workTime - job.workTime;
		return this.start - job.start;
	}
	
  // for debugging
	@Override
	public String toString() {
		return String.format("(time=%d wait=%d)", workTime, wait);
	}
}
