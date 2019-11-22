/*
 * [백준][8983] 사냥꾼
 * 68260 KB	1044 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main {

	public static void main(String[] args) throws Exception {
		int N, M, L, hunters[], animals[][];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		hunters = new int[M];
		animals = new int[N][2];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++)
			hunters[i] = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			animals[i][0] = Integer.parseInt(st.nextToken());
			animals[i][1] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		Arrays.sort(hunters);
		Arrays.sort(animals, (a, b) -> a[0] - b[0]);
		
		int count = 0;
    // 동물에 사대를 대응시킴 (동물 위치에 따라 사냥 위치를 선택)
		for(int a=0; a<N; a++) {
			int h = find(animals[a][0], hunters);
			if(Math.abs(hunters[h] - animals[a][0]) + animals[a][1] <= L) count++;
		}
		System.out.print(count);
	}
	
	static int find(int x, int[] locations) {
		int left = 0;
		int right = locations.length - 1;
		while(left < right) {
			int mid = (left + right) / 2;
			if(locations[mid] == x) return mid;
			if(locations[mid] < x) {
				if(left == mid) break;
				left = mid;
			}
			else {
				if(right == mid) break;
				right = mid;
			}
		}
		return x - locations[left] < locations[right] - x ? left : right;
	}
}
