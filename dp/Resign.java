package dp;
import java.util.Scanner;


public class Resign {
    public static void main(String[] args) {
        int N, schedule[][], dp[], max;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        schedule = new int[N+2][2];
        for(int i=1; i<=N; i++) {
        	for(int j=0; j<2; j++) {
        		schedule[i][j] = sc.nextInt();
        	}
        }
        sc.close();
        
        dp = new int[N+2];
        max = 0;
        for(int day=N; day>0; day--) {
        	if(day+schedule[day][0]>N+1) {
        		if(day<N) dp[day] = dp[day+1];
        		continue;
        	}
        	dp[day] = Math.max(dp[day+1], dp[day+schedule[day][0]] + schedule[day][1]);
        	max = Math.max(max, dp[day]);
        }
        System.out.println(max);
    }
}
