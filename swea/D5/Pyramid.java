/*
 * [SWEA][4112] 이상한 피라미드 탐험
 * 21,048 kb  131 ms
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Solution {
	static int start[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		start = new int[142];
		start[0] = 1;
    
		// dp
		for(int i=1; i<142; i++)
			start[i] = start[i-1] + i;
		
		for(int test_case=1; test_case<=T; test_case++) {
			int from, to;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			if(to < from) {
				int temp = from;
				from = to;
				to = temp;
			}
			
			int fs = findStart(from);
			int ts = findStart(to);
			int fromLeft = (to - start[ts]) - (from - start[fs]);
			int fromRight = (start[ts+1] - to) - (start[fs+1] - from);
			if(fromLeft == 1) fromLeft = 0;
			if(fromRight == 1) fromRight = 0;
			int answer;
			if(Math.abs(fromLeft) < Math.abs(fromRight)) answer = fromLeft;
			else answer = fromRight;
			
			// from이 더 많이 나와있음
			if(answer < 0) {
				answer = -answer;
				answer += (ts - fs);
			} else {
				answer = (ts - fs);
			}
			
			// write
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(answer);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	// binary search
	static int findStart(int goal) {
		int left = 0;
		int right = start.length-1;
		while(left < right) {
			int mid = (left + right) / 2 + 1;
			if(start[mid] <= goal && goal < start[mid+1]) return mid;
			if(goal < start[mid]) right = mid-1;
			else left = mid;
		}
		return left;
	}
	

}
