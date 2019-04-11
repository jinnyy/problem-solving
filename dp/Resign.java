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
        // 며칠을 사용해야 하는지에 따라 계산하기 때문에 마지막 날부터 거꾸로 계산해나간다.
        for(int day=N; day>0; day--) {
            // 예외처리
        	if(day+schedule[day][0]>N+1) {
        		if(day<N) dp[day] = dp[day+1];
        		continue;
        	}
            // 계산
            // - 오늘 상담을 하지 않거나(다음 날의 값과 같다)
            // - 오늘 상담을 하고 schedule[day][0]일 후에 다시 상담을 재개
        	dp[day] = Math.max(dp[day+1], dp[day+schedule[day][0]] + schedule[day][1]);
        	max = Math.max(max, dp[day]);
        }
        System.out.println(max);
    }
}
