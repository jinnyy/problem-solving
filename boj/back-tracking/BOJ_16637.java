/*
 * [백준][16637] 괄호 추가하기
 * 12912 KB	72 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static final int MIN = Integer.MIN_VALUE;
	static int N, max;
	static String eq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eq = br.readLine();
		br.close();
		
		max = MIN;
		divide(0, new boolean[N/2 + 1]);
		System.out.print(max);
	}
	
	static void divide(int depth, boolean[] divided) {
		if(depth == N/2 + 1) {
			int answer = getAnswer(divided);
			max = Math.max(max, answer);
			return;
		}
		
		divide(depth+1, divided);
		if(depth>1 && !divided[depth-1]) {
			divided[depth] = true;
			divide(depth+1, divided);
		}
		divided[depth] = false;
	}
	
	static int getAnswer(boolean[] divided) {
		int answer, pAnswer, cur, prev;
		answer = 0;
		pAnswer = 0;
		cur = 0;
		char op = '+', prevOp = '+';
		for(int i=0; i<N; i++) {
			if(i%2 == 0) {
				// 숫자
				prev = cur;
				cur = eq.charAt(i) - '0';
				if(divided[i/2]) {
					answer = pAnswer;
					cur = operate(prev, op, cur);
					answer = operate(answer, prevOp, cur);
					cur = 0;
				} else {
					pAnswer = answer;
					answer = operate(answer, op, cur);
				}
			} else {
				// 연산자
				prevOp = op;
				op = eq.charAt(i);
			}
		}
		return answer;
	}
	
	
	static int operate(int answer, char op, int number) {
		switch(op) {
		case '+':
			answer += number;
			break;
		case '-':
			answer -= number;
			break;
		case '*':
			answer *= number;
			break;
		}
		return answer;
	}
}
