/*
 * [백준][16325] 나무 재테크
 * 266536 KB	1060 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;


public class Main {
	static final int dir[] = {-1, 0, 1};
	static ArrayList<ArrayList<PriorityQueue<Tree>>> treeMap;
	static int N, map[][];

	public static void main(String[] args) throws Exception {
		int M, K, A[][];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				map[r][c] = 5;
			}
		}
		treeMap = new ArrayList<ArrayList<PriorityQueue<Tree>>>();
		for(int i=0; i<N; i++) {
			ArrayList<PriorityQueue<Tree>> row = new ArrayList<PriorityQueue<Tree>>();
			for(int j=0; j<N; j++) {
				row.add(new PriorityQueue<Tree>());
			}
			treeMap.add(row);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			treeMap.get(x).get(y).add(new Tree(age));
		}
		br.close();
		
		
		// compute
		for(int year=1; year<=K; year++) {
			springSummer();
			fall();
			winter(A);
		}
		
		
		// write
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				cnt += treeMap.get(i).get(j).size();
			}
		}
		OutputStreamWriter ow = new OutputStreamWriter(System.out);
		ow.append(String.valueOf(cnt));
		ow.close();
	}
	
	private static void springSummer() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				PriorityQueue<Tree> trees = treeMap.get(i).get(j);
				PriorityQueue<Tree> next = new PriorityQueue<Tree>();
				boolean dead = false;
				while(!trees.isEmpty()) {
					Tree t = trees.poll();
					if(!dead) {
						if(t.age <= map[i][j]) {
							map[i][j] -= t.age;
							t.age++;
							next.add(t);
						} else dead = true;
					}
					
					// 죽었으면 계속 더해줌
					if(dead) map[i][j] += t.age / 2;
				}
				treeMap.get(i).set(j, next);
			}
		}
	}
	
	private static void fall() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				for(Tree tree : treeMap.get(y).get(x)) {
					if(tree.age % 5 == 0) {
						for(int dy : dir) {
							for(int dx : dir) {
								if(dy == 0 && dx == 0) continue;
								int ny = y + dy;
								int nx = x + dx;
								if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
								treeMap.get(ny).get(nx).add(new Tree(1));
							}
						}
					}
				}
			}
		}
	}

	private static void winter(int A[][]) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
}

class Tree implements Comparable<Tree> {
	int age;
	
	Tree(int age) {
		this.age = age;
	}
	
	@Override
	public int compareTo(Tree t) {
		return this.age - t.age;
	}
}
