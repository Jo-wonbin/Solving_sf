package BOJ.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1158_요세푸스문제_queue {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			q.add(i);
		}
		sb.append("<");
		int temp = 1;
		while (!q.isEmpty()) {
			if (temp == k) {
				sb.append(q.poll());
				temp = 1;
				sb.append(", ");
			} else {
				int tp = q.poll();
				q.add(tp);
				temp++;
			}
		}
		
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		System.out.println(sb);
		br.close();
	}
}
