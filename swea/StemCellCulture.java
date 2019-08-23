/*
 * [SWEA][5653] 줄기세포배양
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo
 */
package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.HashSet;


public class StemCellCulture {
	static final int START = 200;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			// read
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Cell[][] map = new Cell[400][400];
			LinkedList<Cell> Q = new LinkedList<Cell>();
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					int e = Integer.parseInt(st.nextToken());
					if(e<1) continue;
					map[START+i][START+j] = new Cell(START+i, START+j, e);
					Q.add(map[START+i][START+j]);
				}
			}
			
			// calculate
			// status >= -1 일때만 넣고
			// -1일 때에는 새로운 cell 넣음
			// -2이면 dead -> Q에 넣지 않음
			for(int time=0; time<K; time++) {
				LinkedList<Cell> Q2 = new LinkedList<Cell>();
				HashSet<Cell> newSet = new HashSet<Cell>();
				while(!Q.isEmpty()) {
					Cell cur = Q.poll();
					LinkedList<Cell> newCells = cur.culture();
					for(Cell cell : newCells) {
						if(map[cell.y][cell.x] != null) {
							Cell prev = map[cell.y][cell.x];
							if(newSet.contains(prev) && prev.energy < cell.energy) {
								newSet.remove(prev);
								map[cell.y][cell.x] = cell;
								newSet.add(cell); // prev를 빼고 cell을 넣는다.
							}
						} else {
							map[cell.y][cell.x] = cell;
							newSet.add(cell);
						}
					}
					if(cur.status>-cur.energy) {
						Q2.add(cur);
					}
				}
				Q = Q2;
				for(Cell cell : newSet) {
					Q.add(cell);
				}
			}
			
			// write
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(Q.size());
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}



class Cell {
	static int[] dy = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dx = {0, 0, -1, 1};
	int y, x, energy, status; // status: energy=초기, -1=번식, -energy=죽음
	
	Cell(int y, int x, int energy) {
		this.y = y;
		this.x = x;
		this.energy = energy;
		this.status = energy;
	}
	
	LinkedList<Cell> culture() {
		LinkedList<Cell> newCells = new LinkedList<Cell>();
		if(--this.status==-1) {
			for(int i=0; i<4; i++) {
				newCells.add(new Cell(this.y+dy[i], this.x+dx[i], this.energy));
			}
		}
		return newCells;
	}
	
	@Override
	public String toString() {
		return String.format("%d", this.status);
	}
}
