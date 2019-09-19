/*
 * [백준][10773] 제로
 * 23060 KB	176 ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		Stack st = new Stack();
		for(int i=0; i<K; i++) {
			int cur = Integer.parseInt(br.readLine());
			if(cur==0) st.pop();
			else st.push(cur);
		}
		br.close();
		
		int result = 0;
		while(st.size > 0) {
			result += st.pop();
		}
		System.out.println(result);
	}
}


class Stack {
	StackNode tail;
	int size;
	
	Stack() {
		this.size = 0;
	}
	
	void push(int val) {
		StackNode newNode = new StackNode(val);
		newNode.prev = this.tail;
		this.tail = newNode;
		size++;
	}
	
	int pop() {
		StackNode ret = this.tail;
		this.tail = this.tail.prev;
		this.size--;
		return ret.val;
	}
}

class StackNode {
	StackNode prev;
	int val;
	
	StackNode(int val) {
		this.val = val;
	}
}
