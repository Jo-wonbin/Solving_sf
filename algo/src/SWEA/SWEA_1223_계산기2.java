package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA_1223_계산기2 {

	static Stack<Integer> s = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int k = 1; k <= 10; k++) {

			int N = Integer.parseInt(br.readLine());

			char ch[] = br.readLine().toCharArray();

			int sum = 0;
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				if (flag) {
					s.add(s.pop() * (ch[i] - '0'));
					flag = false;
				} else if (Character.isDigit(ch[i])) {
					s.add(ch[i] - '0');
				} else if (ch[i] == '*') {
					flag = true;
				}
			}
			while (!s.isEmpty()) {
				int a = s.pop();
				sum += a;
			}

			sb.append("#").append(k).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}
