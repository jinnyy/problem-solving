/*
 * [SWEA][5648] 원자 소멸 시뮬레이션
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRFInKex8DFAUo
 */
package swea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;



public class AtomicExtinctionSimulation {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		int[][] map = new int[4002][4002];
		boolean[] visited = new boolean[1001];
		boolean[] outOfRange = new boolean[1001];
		
		for(int test_case=1; test_case<=T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			Atom[] atoms = new Atom[N+1];
			for(int i=1; i<=N; i++) {
				atoms[i] = new Atom(br.readLine());
			}
			
			// calculation
			int energy = 0;
			for(int time=0; time<4001; time++) {
				for(int atom=1; atom<=N; atom++) {
					if(outOfRange[atom] || visited[atom]) continue;
					Atom cur = atoms[atom];
					cur.move();
					if(cur.y>1000 || cur.x>1000 || cur.y<-1000 || cur.x<-1000) {
						outOfRange[atom] = true;
						continue;
					}
					int cy = (int) (cur.y*2 + 2000);
					int cx = (int) (cur.x*2 + 2000);
					
					if(map[cy][cx] != 0) {
						visited[map[cy][cx]] = true;
						visited[atom] = true;
					} else {
						map[cy][cx] = atom;
					}
				}
				// map 초기화 (메모리 초과를 방지하기 위해)
				for(int atom=1; atom<=N; atom++) {
					if(outOfRange[atom]) continue;
					map[(int) (atoms[atom].y*2 + 2000)][(int) (atoms[atom].x*2 + 2000)] = 0;
				}
			}
			for(int i=0; i<=N; i++) {
				if(visited[i]) {
					energy += atoms[i].energy;
				}
				visited[i] = false;
				outOfRange[i] = false;
			}
			
			// print
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(energy);
			bw.append(sb.toString());
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}

class Atom {
	static final double[] dy = {0.5, -0.5, 0, 0};
	static final double[] dx = {0, 0, -0.5, 0.5};
	float y, x;
	int dir, energy;
	
	Atom(String str) {
		StringTokenizer st = new StringTokenizer(str, " ");
		this.x = Integer.parseInt(st.nextToken());
		this.y = Integer.parseInt(st.nextToken());
		this.dir = Integer.parseInt(st.nextToken());
		this.energy = Integer.parseInt(st.nextToken());
	}
	
	void move() {
		this.y += dy[this.dir];
		this.x += dx[this.dir];
	}
	
	// for debugging
	@Override
	public String toString() {
		return String.format("(%.1f, %.1f)", this.x, this.y);
	}
}

