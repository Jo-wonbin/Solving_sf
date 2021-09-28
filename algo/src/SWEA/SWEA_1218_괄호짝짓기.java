package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1218_괄호짝짓기 {

	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cnt = 1;
		while (cnt <= 10) {
			int n = Integer.parseInt(br.readLine());
			String N = br.readLine();
			boolean flag = true;
			for (int i = 0; i < N.length(); i++) {

				if (stack.isEmpty() || N.charAt(i) == '{' || N.charAt(i) == '(' || N.charAt(i) == '<'
						|| N.charAt(i) == '[') {
					stack.push(N.charAt(i));
					continue;
				}

				if (stack.peek() == '{' && N.charAt(i) == '}') {
					stack.pop();
				} else if (stack.peek() == '[' && N.charAt(i) == ']') {
					stack.pop();
				} else if (stack.peek() == '(' && N.charAt(i) == ')') {
					stack.pop();
				} else if (stack.peek() == '<' && N.charAt(i) == '>') {
					stack.pop();
				} else {
					flag = false;
					break;
				}

			}
			if (flag) {
				System.out.printf("#%d 1\n", cnt);
			} else {
				System.out.printf("#%d 0\n", cnt);
			}
			cnt++;
			stack.clear();
		}

		br.close();
	}

}
