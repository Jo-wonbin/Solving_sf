package BOJ.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493_탑 {
	static class top {
		int n, height;

		public top(int num, int height) {
			super();
			this.n = num;
			this.height = height;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<top> s = new Stack<>();
		for (int i = 1; i <= N; i++) {
			int height = Integer.parseInt(st.nextToken());

			if (s.isEmpty()) {
				sb.append("0 ");
				s.add(new top(i, height));
			} else {
				while (true) {
					if (s.isEmpty()) { // 스택이 비어있다면, 0을 출력하고 탑을 push한다.
						sb.append("0 ");
						s.push(new top(i, height));
						break;
					}

					top t = s.peek();

					if (t.height > height) { // peek한 탑의 높이가 현재 탑의 높이보다 높다면,
						sb.append(t.n + " "); // peek한 탑의 번호를 출력하고,
						s.push(new top(i, height)); // 현재 탑을 스택에 push한다.
						break;
					} else { // peek한 탑의 높이가 현재 탑의 높이보다 낮다면,
						s.pop(); // 스택에서 pop하고 다시 반복문을 돌린다.
					}
				}
			}

		}

		System.out.println(sb);

		br.close();
	}

}
