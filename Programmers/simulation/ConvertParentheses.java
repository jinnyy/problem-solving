/*
 * [프로그래머스][LEV.2] 괄호 변환
 */
import java.util.Stack;


class Solution {
	
	static final char LEFT = '(';
	static final char RIGHT = ')';

	
	public String solution(String p) {
		if(isCorrect(p)) return p;
		return convert(p);
	}
	
	private static String convert(String p) {
		if(p.length() == 0) return "";
		int mid = findMid(p) + 1;
		String u = p.substring(0, mid);
		String v = p.substring(mid);
		if(isCorrect(u)) {
			return u + convert(p.substring(mid));
		}
		StringBuilder sb = new StringBuilder();
		sb.append(LEFT);
		sb.append(convert(v));
		sb.append(RIGHT);
		for(int i=1; i<u.length()-1; i++) {
			sb.append(u.charAt(i)==LEFT ? RIGHT : LEFT);
		}
		return sb.toString();
	}
	
	private static boolean isCorrect(String p) {
		Stack<Character> st = new Stack<Character>();
		for(int i=0; i<p.length(); i++) {
			char cur = p.charAt(i);
			if(cur == '(') {
				st.add(cur);
			} else {
				if(st.size()==0 || st.pop() != '(') return false;
			}
		}
		return st.size()==0;
	}
	
	private static int findMid(String p) {
		int idx = -1;
		int left = 0;
		int right = 0;
		while(idx<0 || left != right) {
			idx++;
			char cur = p.charAt(idx);
			if(cur == LEFT) left++;
			else right++;
		}
		return idx;
	}
	
}
