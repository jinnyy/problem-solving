/*
 * [백준][2170] 선 긋기
 * 305328 KB	1728 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;


public class LineDrawing {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Line> minHeap = new PriorityQueue<Line>();
		// O(N*logN)
		for(int i=0; i<N; i++) {
			minHeap.add(new Line(br.readLine()));
		}
		br.close();
		
		// O(N*logN)
		int min = 0, max = -1000000001, length = 0;
		while(!minHeap.isEmpty()) {
			Line cur = minHeap.poll();
			if(cur.x1 < max) {
				if(max < cur.x2) {
					length += cur.x2 - max;
					max = cur.x2;
				}
			} else {
				min = cur.x1;
				max = cur.x2;
				length += (max - min);
			}
		}
		System.out.print(length);
	}

}

class Line implements Comparable<Line> {
	int x1, x2;
	
	Line(String input) {
		StringTokenizer st = new StringTokenizer(input, " ");
		this.x1 = Integer.parseInt(st.nextToken());
		this.x2 = Integer.parseInt(st.nextToken());
	}
	
	@Override
	public int compareTo(Line line) {
		return this.x1 - line.x1;
	}
}
