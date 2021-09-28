package BOJ.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9012_괄호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> s = new Stack<>();

		int N = Integer.parseInt(br.readLine());

		while (N-- > 0) {
			boolean flag = true;
			String n = br.readLine();
			for (int i = 0; i < n.length(); i++) {
				if (s.isEmpty() && n.charAt(i) == ')') {
					flag = false;
					break;
				}

				if (n.charAt(i) == '(') {
					s.push(n.charAt(i));
					continue;
				}

				if (s.peek() == '(' && n.charAt(i) == ')') {
					s.pop();
				}

			}
			if (flag) {
				if (s.isEmpty())
					System.out.println("YES");
				else
					System.out.println("NO");
			} else {
				System.out.println("NO");
			}
			s.clear();
		}

		br.close();
	}

}
