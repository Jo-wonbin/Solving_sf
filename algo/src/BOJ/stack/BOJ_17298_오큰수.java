package BOJ.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298_오큰수 {
	static class top {
		int num, size;

		public top(int num, int size) {
			super();
			this.num = num;
			this.size = size;
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<top> s = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		Arrays.fill(arr, -1);
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());

			if (s.isEmpty()) {
				s.push(new top(i, now));
			} else {
				while (true) {
					if (s.isEmpty()) {
						s.push(new top(i, now));
						break;
					}
					top top = s.peek();

					if (top.size < now) {
						arr[top.num] = now;
						s.pop();
					} else {
						s.push(new top(i, now));
						break;
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb);

		br.close();
	}

}
