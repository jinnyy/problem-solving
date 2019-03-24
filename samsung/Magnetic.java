package samsung;
/*
 * 특이한 자석
 * https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH&
 */
import java.util.Scanner;



public class Magnetic {

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T, K, Figs[][], turns[][], which[], changeAmount[];
		T = sc.nextInt();

		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			K = sc.nextInt();
			Figs = new int[4][8];
			turns = new int[K][2];
			which = new int[4];
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<8; j++) {
					Figs[i][j] = sc.nextInt();
				}
			}
			
			for(int i=0; i<K; i++) {
				turns[i][0] = sc.nextInt()-1;
				turns[i][1] = sc.nextInt();
			}
						
						
			// calculate			
			for(int turnNum=0; turnNum<K; turnNum++) {
				changeAmount = new int[which.length];
				// [0] 바퀴번호
				// [1] 방향
				int wheel = turns[turnNum][0];
				int dir = turns[turnNum][1];
				int left = wheel-1;
				int right = wheel+1;
				
				int cur = wheel;
				int whichCur1, whichCur2, whichLeft, whichRight;
				// left 비교, right 비교
				while(left>=0 && cur>0) {
					whichCur1 = convert(which[cur], -2, 8);
					whichLeft = convert(which[left], 2, 8);
					if(Figs[cur][whichCur1] != Figs[left][whichLeft])
						changeAmount[left] = convert(which[left], dir, 8) - which[left];
					else
						break;
					cur--;
					left--;
					dir *= -1;
				}
				
				dir = turns[turnNum][1];
				cur = wheel;
				while(right<=3 && cur<3) {
					whichCur2 = convert(which[cur], 2, 8);
					whichRight = convert(which[right], -2, 8);
					if(Figs[cur][whichCur2] != Figs[right][whichRight])
						changeAmount[right] = convert(which[right], dir, 8) - which[right];
					else
						break;
					cur++;
					right++;
					dir *= -1;
				}
				
				dir = turns[turnNum][1];
				changeAmount[wheel] = convert(which[wheel], -1*dir, 8) - which[wheel];
				
				
				for (int j=0; j<changeAmount.length; j++) {
					if(changeAmount[j]!=0) which[j] = convert(which[j], changeAmount[j], 8);
				}
			}
			int answer = 0;
			int cnt = 1;
			for(int i=0; i<4; i++) {
				answer += Figs[i][which[i]] * cnt;
				cnt *= 2;
			}
			System.out.println("#" + test_case + " " + answer);
		}
		sc.close();
	}
	
	static private int convert(int num, int dir, int limit) {
		int ans = num;
		if (dir < 0) ans += ans+dir<0? dir+limit: dir;
		else ans += ans+dir>limit-1? dir-limit: dir;
		return ans;
	}

}
