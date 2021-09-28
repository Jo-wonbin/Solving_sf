package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_3499_퍼펙트셔플 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<String> q1 = new LinkedList<>();
		Queue<String> q2 = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());

		for (int k = 1; k <= tc; k++) {
			int N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				if (N % 2 == 1) {
					if (i <= (N - 1) / 2) {
						q1.add(st.nextToken());
					} else {
						q2.add(st.nextToken());
					}
				} else {
					if (i < N / 2) {
						q1.add(st.nextToken());
					} else {
						q2.add(st.nextToken());
					}
				}
			}
			sb.append("#").append(k).append(" ");
			int size = q1.size();
			for (int i = 0; i < size; i++) {
				if (!q1.isEmpty()) {
					sb.append(q1.poll()).append(" ");
				}
				if (!q2.isEmpty()) {
					sb.append(q2.poll()).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();

	}

}
