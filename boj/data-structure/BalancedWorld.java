/*
 * [백준][4949] 균형잡힌 세상
 * 18044 KB	136 ms (java 7등)
 */
package dataStructure;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class BalancedWorld {
	static final String YES="yes", NO="no";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = br.readLine();
		while(!(line.length()==1 && line.equals("."))) {
			bw.append(isBalanced(line) ? YES : NO);
			bw.newLine();
			line = br.readLine();
		}
		br.close();
		bw.close();
	}
	
	static boolean isBalanced(String line) {
		Stack st = new Stack(line.length());
		for(int i=0; i<line.length(); i++) {
			char cur = line.charAt(i);
			if(cur=='[' || cur=='(') {
				st.push(cur);
			} else if(cur==']') {
				if(st.pop() != '[') return false;
			} else if(cur==')') {
				if(st.pop() != '(') return false;
			}
		}
		return st.size==0;
	}

}

class Stack {
	static final char TRASH = 't';
	char[] list;
	int size, maxSize;
	
	Stack(int maxSize) {
		this.maxSize = maxSize;
		this.list = new char[maxSize+1];
		this.size = 0;
	}
	
	boolean push(char c) {
		if(size==maxSize) return false;
		list[size] = c;
		size++;
		return true;
	}
	
	Character pop() {
		if(size==0) return TRASH;
		size--;
		char ret = list[size];
		list[size] = 0;
		return ret;
	}
}
